package com.abzagency.features.signup.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abzagency.core.common.constants.Constants
import com.abzagency.core.common.dispatchers.IoDispatcher
import com.abzagency.core.common.presentation.loadingTask
import com.abzagency.core.common.presentation.performUseCase
import com.abzagency.core.common.response.ErrorData
import com.abzagency.features.signup.domain.usecases.ClearCacheDirUseCase
import com.abzagency.features.signup.domain.usecases.GetUserPositionsFromRemoteUseCase
import com.abzagency.features.signup.domain.usecases.GetUserTokenFromRemoteUseCase
import com.abzagency.features.signup.domain.usecases.SignUpUserUseCase
import com.abzagency.features.signup.domain.usecases.validators.ValidateEmailUseCase
import com.abzagency.features.signup.domain.usecases.validators.ValidateNameUseCase
import com.abzagency.features.signup.domain.usecases.validators.ValidatePhoneUseCase
import com.abzagency.features.signup.domain.usecases.validators.ValidatePhotoUseCase
import com.abzagency.features.signup.models.domain.SignUpDomainModel
import com.abzagency.features.signup.models.presentation.PositionPresentationModel
import com.abzagency.features.signup.models.presentation.toPresentationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val getUserTokenFromRemoteUseCase: GetUserTokenFromRemoteUseCase,
    private val getPUserPositionsFromRemoteUseCase: GetUserPositionsFromRemoteUseCase,
    private val clearCacheDirUseCase: ClearCacheDirUseCase,
    private val signUpUserUseCase: SignUpUserUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validateNameUseCase: ValidateNameUseCase,
    private val validatePhoneUseCase: ValidatePhoneUseCase,
    private val validatePhotoUseCase: ValidatePhotoUseCase,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        SignUpViewModelState(
            name = Constants.EMPTY_STRING,
            email = Constants.EMPTY_STRING,
            phone = Constants.EMPTY_STRING,
            selectedPositionId = Constants.UNDEFINED_INT,
            emailError = null,
            phoneError = null,
            nameError = null,
            photoError = null,
            userPositions = emptyList(),
            isPositionsLoading = false,
            userPhoto = null,
            isPhotoPickerBottomSheetVisible = false,
            responseErrorData = null,
            showSignupSuccess = false
        )
    )
    val uiState: StateFlow<SignUpViewModelState> = _uiState

    fun getUserPositionsFromRemote() {
        viewModelScope.launch(coroutineDispatcher) {
            loadingTask(::setIsLoading) {
                performUseCase(
                    useCase = {
                        getPUserPositionsFromRemoteUseCase()
                    },
                    success = { positions ->
                        _uiState.update { uiState ->
                            uiState.copy(
                                userPositions = positions.map { it.toPresentationModel() },
                                selectedPositionId = if (positions.isNotEmpty()) {
                                    positions.first().id
                                } else Constants.UNDEFINED_INT
                            )
                        }
                    },
                    error = { errorData ->
                        updateErrorData(errorData)
                    }
                )
            }
        }
    }

    fun onNameChange(value: String) {
        _uiState.update { uiState ->
            uiState.copy(
                name = value,
                nameError = null
            )
        }
    }

    fun onEmailChange(value: String) {
        _uiState.update { uiState ->
            uiState.copy(
                email = value,
                emailError = null
            )
        }
    }

    fun onPhoneChange(value: String) {
        _uiState.update { uiState ->
            uiState.copy(
                phone = value,
                phoneError = null
            )
        }
    }

    fun onPositionSelect(positionId: Int) {
        _uiState.update { uiState ->
            uiState.copy(selectedPositionId = positionId)
        }
    }

    fun updateUploadPhotoPickerDialogVisibility(isVisible: Boolean) {
        _uiState.update { uiState ->
            uiState.copy(
                isPhotoPickerBottomSheetVisible = isVisible
            )
        }
    }

    fun updateUserPhoto(photo: File?) {
        _uiState.update { uiState ->
            uiState.copy(
                userPhoto = photo,
                photoError = null
            )
        }
    }

    fun validateUserDataAndGetUserToken() {
        if (isDataValid()) {
            getUserTokenAndSignup()
        }
    }

    private fun isDataValid(): Boolean {
        with(_uiState.value) {
            val emailValidationData = validateEmailUseCase(this.email)
            val nameValidationData = validateNameUseCase(this.name)
            val phoneValidationData = validatePhoneUseCase(this.phone)
            val photoValidationData = validatePhotoUseCase(this.userPhoto)

            _uiState.update { uiState ->
                uiState.copy(
                    emailError = emailValidationData.errorMessage,
                    nameError = nameValidationData.errorMessage,
                    phoneError = phoneValidationData.errorMessage,
                    photoError = photoValidationData.errorMessage
                )
            }

            return emailValidationData.isValid &&
                    nameValidationData.isValid &&
                    photoValidationData.isValid &&
                    phoneValidationData.isValid &&
                    this.selectedPositionId != Constants.UNDEFINED_INT
        }
    }

    private fun getUserTokenAndSignup() {
        viewModelScope.launch(coroutineDispatcher) {
            performUseCase(
                useCase = {
                    getUserTokenFromRemoteUseCase()
                },
                success = { token ->
                    signUpUser(token)
                },
                error = { errorData ->
                    updateErrorData(errorData)
                }
            )
        }
    }

    private fun signUpUser(token: String) {
        _uiState.value.userPhoto?.let { photo ->
            viewModelScope.launch(coroutineDispatcher) {
                performUseCase(
                    useCase = {
                        signUpUserUseCase(
                            token = token,
                            signUpData = SignUpDomainModel(
                                name = _uiState.value.name,
                                email = _uiState.value.email,
                                phone = _uiState.value.phone,
                                positionId = _uiState.value.selectedPositionId,
                                photo = photo
                            )
                        )
                    },
                    success = {
                        clearUiState()
                        updateSignupSuccess(true)
                    },
                    error = { errorData ->
                        updateErrorData(errorData)
                    }
                )
            }
        }
    }

    private fun setIsLoading(isLoading: Boolean) {
        _uiState.update { uiState ->
            uiState.copy(isPositionsLoading = isLoading)
        }
    }

    private fun clearUiState() {
        _uiState.update { uiState ->
            uiState.copy(
                name = Constants.EMPTY_STRING,
                email = Constants.EMPTY_STRING,
                phone = Constants.EMPTY_STRING,
                userPhoto = null,
                selectedPositionId = Constants.UNDEFINED_INT,
                userPositions = emptyList()
            )
        }

        clearCacheDirUseCase()
    }

    fun updateErrorData(errorData: ErrorData?) {
        _uiState.update { uiState ->
            uiState.copy(
                responseErrorData = errorData
            )
        }
    }

    fun updateSignupSuccess(isSignupSuccess: Boolean) {
        _uiState.update { uiState ->
            uiState.copy(
                showSignupSuccess = isSignupSuccess
            )
        }
    }
}

data class SignUpViewModelState(
    val name: String,
    val email: String,
    val phone: String,
    val selectedPositionId: Int,
    val nameError: String?,
    val emailError: String?,
    val phoneError: String?,
    val photoError: String?,
    val userPositions: List<PositionPresentationModel>,
    val isPositionsLoading: Boolean,
    val isPhotoPickerBottomSheetVisible: Boolean,
    val userPhoto: File?,
    val responseErrorData: ErrorData?,
    val showSignupSuccess: Boolean
)