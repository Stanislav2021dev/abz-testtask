package com.abzagency.features.signup.domain.usecases.validators

import android.content.Context
import com.abzagency.core.designsystem.ui.textfield.TextFieldErrors
import com.abzagency.features.signup.models.domain.UserValidationDataDomainModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ValidateNameUseCase @Inject constructor(
    @ApplicationContext private val context: Context
) {
    operator fun invoke(name: String): UserValidationDataDomainModel =
        when {
            name.isEmpty() ->  UserValidationDataDomainModel(
                isValid = false,
                errorMessage = context.getString(TextFieldErrors.requiredFieldErrorId)
            )

            name.length < MIN_NAME_LENGTH -> UserValidationDataDomainModel(
                isValid = false,
                errorMessage = context.getString(TextFieldErrors.nameIsShortErrorId)
            )

            name.length > MAX_NAME_LENGTH -> UserValidationDataDomainModel(
                isValid = false,
                errorMessage = context.getString(TextFieldErrors.nameIsLongErrorId)
            )

            else -> UserValidationDataDomainModel(
                isValid = true,
                errorMessage = null
            )
        }

    companion object {
        private const val MIN_NAME_LENGTH = 2
        private const val MAX_NAME_LENGTH = 60
    }
}