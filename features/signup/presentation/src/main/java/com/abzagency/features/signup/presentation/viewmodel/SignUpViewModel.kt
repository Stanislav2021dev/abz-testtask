package com.abzagency.features.signup.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abzagency.core.common.constants.Constants
import com.abzagency.core.common.dispatchers.IoDispatcher
import com.abzagency.core.common.presentation.loadingTask
import com.abzagency.core.common.presentation.performUseCase
import com.abzagency.features.users.domain.usecases.ClearCacheDirUseCase
import com.abzagency.features.users.domain.usecases.GetUserPositionsFromRemoteUseCase
import com.abzagency.features.users.domain.usecases.GetUserTokenFromRemoteUseCase
import com.abzagency.features.users.domain.usecases.SignUpUserUseCase
import com.abzagency.features.users.domain.usecases.validators.ValidateEmailUseCase
import com.abzagency.features.users.domain.usecases.validators.ValidateNameUseCase
import com.abzagency.features.users.domain.usecases.validators.ValidatePhoneUseCase
import com.abzagency.features.users.domain.usecases.validators.ValidatePhotoUseCase
import com.abzagency.features.users.models.domain.SignUpDomainModel
import com.abzagency.features.users.models.presentation.PositionPresentationModel
import com.abzagency.features.users.models.presentation.toPresentationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
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
            name = Constants.emptyString,
            email = Constants.emptyString,
            phone = Constants.emptyString,
            selectedPositionId = Constants.undefinedInt,
            emailError = null,
            phoneError = null,
            nameError = null,
            photoError = null,
            userPositions = emptyList(),
            isPositionsLoading = true,
            userPhoto = null,
            isPhotoPickerBottomSheetVisible = false
        )
    )
    val uiState: StateFlow<SignUpViewModelState> = _uiState

    init {
        getUserPositionsFromRemote()
    }

    private fun getUserPositionsFromRemote() {
        viewModelScope.launch(coroutineDispatcher) {
            loadingTask(::setIsLoading) {
                performUseCase(
                    useCase = {
                        getPUserPositionsFromRemoteUseCase()
                    },
                    success = { positions ->
                        // Delay for shimmers
                        delay(1000)

                        _uiState.update { uiState ->
                            uiState.copy(
                                userPositions = positions.map { it.toPresentationModel() },
                                selectedPositionId = if (positions.isNotEmpty()) {
                                    positions.first().id
                                } else Constants.undefinedInt
                            )
                        }
                    },
                    error = {

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
                    this.selectedPositionId != null
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
                error = {
                    // TODO add handling errors
                }
            )
        }
    }

    private fun signUpUser(token: String) {
        viewModelScope.launch(coroutineDispatcher) {
            performUseCase(
                useCase = {
                    signUpUserUseCase(
                        token = token,
                        signUpData = SignUpDomainModel(
                            name = _uiState.value.name.orEmpty(),
                            email = _uiState.value.email.orEmpty(),
                            phone = _uiState.value.phone.orEmpty(),
                            photo = _uiState.value.userPhoto!!,
                            positionId = _uiState.value.selectedPositionId!!
                        )
                    )
                },
                success = {
                    clearUiState()
                },
                error = {
                    // TODO add handling errors
                }
            )
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
                name = Constants.emptyString,
                email = Constants.emptyString,
                phone = Constants.emptyString,
                userPhoto = null,
                selectedPositionId = Constants.undefinedInt
            )
        }

        clearCacheDirUseCase()
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
)