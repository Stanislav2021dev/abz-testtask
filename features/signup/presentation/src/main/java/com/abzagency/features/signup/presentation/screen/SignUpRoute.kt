package com.abzagency.features.signup.presentation.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abzagency.core.designsystem.resources.Colors
import com.abzagency.core.designsystem.resources.Dimens
import com.abzagency.core.designsystem.resources.Typography
import com.abzagency.core.designsystem.resources.backgroundPrimary
import com.abzagency.core.designsystem.resources.primary
import com.abzagency.core.designsystem.resources.textPrimary
import com.abzagency.core.designsystem.ui.common.Header
import com.abzagency.core.designsystem.ui.keyboard.clearFocusOnKeyboardDismiss
import com.abzagency.core.designsystem.ui.textfield.CommonTextField
import com.abzagency.features.signup.presentation.R
import com.abzagency.features.signup.presentation.components.ImagePicker
import com.abzagency.features.signup.presentation.components.ImagePickerContainer
import com.abzagency.features.signup.presentation.components.PositionContainer
import com.abzagency.features.signup.presentation.components.PositionShimmers
import com.abzagency.features.signup.presentation.viewmodel.SignUpViewModel
import com.abzagency.features.signup.presentation.viewmodel.SignUpViewModelState
import java.io.File

@RequiresApi(Build.VERSION_CODES.KITKAT)
@Composable
internal fun SignUpRoute(
    viewModel: SignUpViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SignUpScreen(
        uiState = uiState,
        onNameChange = viewModel::onNameChange,
        onEmailChange = viewModel::onEmailChange,
        onPhoneChange = viewModel::onPhoneChange,
        onPositionSelect = viewModel::onPositionSelect,
        onSignUpClick = viewModel::validateUserDataAndGetUserToken,
        updateUploadPhotoPickerDialogVisibility = viewModel::updateUploadPhotoPickerDialogVisibility,
        onClearPhotoClick = { viewModel.updateUserPhoto(null) },
        updateUserPhoto = viewModel::updateUserPhoto
    )
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
@Composable
internal fun SignUpScreen(
    uiState: SignUpViewModelState,
    onEmailChange: (value: String) -> Unit,
    onNameChange: (value: String) -> Unit,
    onPhoneChange: (value: String) -> Unit,
    onPositionSelect: (id: Int) -> Unit,
    updateUploadPhotoPickerDialogVisibility: (isVisible: Boolean) -> Unit,
    onClearPhotoClick: () -> Unit,
    onSignUpClick: () -> Unit,
    updateUserPhoto: (photo: File?) -> Unit
) {
    val focusManager = LocalFocusManager.current

    if (uiState.isPhotoPickerBottomSheetVisible) {
        ImagePicker(
            onResult = updateUserPhoto,
            onDismiss = { updateUploadPhotoPickerDialogVisibility(false) }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(Colors.backgroundPrimary()),
    ) {
        Header(title = stringResource(id = R.string.header_title))

        Spacer(modifier = Modifier.height(Dimens.spacingLarge))

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = Dimens.spacingNormal)
        ) {
            CommonTextField(
                modifier = Modifier.clearFocusOnKeyboardDismiss(),
                value = uiState.name.orEmpty(),
                hint = stringResource(id = R.string.name_hint),
                onValueChange = onNameChange,
                error = uiState.nameError
            )

            Spacer(modifier = Modifier.height(Dimens.spacingNormalSpecial))

            CommonTextField(
                modifier = Modifier.clearFocusOnKeyboardDismiss(),
                value = uiState.email.orEmpty(),
                hint = stringResource(id = R.string.email_hint),
                onValueChange = onEmailChange,
                error = uiState.emailError
            )

            Spacer(modifier = Modifier.height(Dimens.spacingNormalSpecial))

            CommonTextField(
                modifier = Modifier.clearFocusOnKeyboardDismiss(),
                value = uiState.phone.orEmpty(),
                hint = stringResource(id = R.string.phone_hint),
                onValueChange = onPhoneChange,
                error = uiState.phoneError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(Dimens.spacingSmall))

            Text(
                text = stringResource(id = R.string.select_position_title),
                color = Colors.textPrimary(),
                style = Typography.body2
            )

            Spacer(modifier = Modifier.height(Dimens.spacingNormal))

            if (uiState.isPositionsLoading) {
                repeat(4) {
                    PositionShimmers()
                }
            } else {
                uiState.userPositions.forEach { position ->
                    uiState.selectedPositionId?.let { id ->
                        PositionContainer(
                            position = position,
                            currentSelectedPositionId = id,
                            onPositionSelect = onPositionSelect
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(Dimens.spacingBig))

            ImagePickerContainer(
                photo = uiState.userPhoto,
                uploadPhoto = {
                    updateUploadPhotoPickerDialogVisibility(true)
                    focusManager.clearFocus()
                },
                clearPhoto = {
                    onClearPhotoClick()
                    focusManager.clearFocus()
                },
                error = uiState.photoError
            )

            Spacer(modifier = Modifier.height(Dimens.spacingNormal))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.width(Dimens.buttonWidth),
                    onClick = {
                        focusManager.clearFocus()
                        onSignUpClick()
                    },
                    colors = ButtonDefaults.buttonColors().copy(
                        containerColor = Colors.primary()
                    )
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = Dimens.spacingNormalSpecial),
                        text = stringResource(id = R.string.signup_btn_text),
                        color = Colors.textPrimary(),
                        style = Typography.body1SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(Dimens.bottomBarHeight + Dimens.spacingBig))
        }
    }
}

@Composable
@Preview
private fun SignUpScreenPreview() {
    SignUpScreen(
        onEmailChange = {},
        onNameChange = {},
        onPhoneChange = {},
        onPositionSelect = {},
        onSignUpClick = {},
        updateUploadPhotoPickerDialogVisibility = {},
        onClearPhotoClick = {},
        updateUserPhoto = {},
        uiState = SignUpViewModelState(
            name = "Name",
            email = "Email",
            phone = "+38012345678",
            selectedPositionId = 1,
            emailError = null,
            nameError = null,
            phoneError = null,
            userPositions = emptyList(),
            isPositionsLoading = false,
            isPhotoPickerBottomSheetVisible = false,
            userPhoto = null,
            photoError = null
        )
    )
}