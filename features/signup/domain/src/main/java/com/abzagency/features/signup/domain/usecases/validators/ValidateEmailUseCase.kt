package com.abzagency.features.signup.domain.usecases.validators

import android.content.Context
import android.util.Patterns
import com.abzagency.core.designsystem.ui.textfield.TextFieldErrors
import com.abzagency.features.signup.models.domain.UserValidationDataDomainModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor(
    @ApplicationContext private val context: Context
) {
    operator fun invoke(email: String): UserValidationDataDomainModel {
        return when {
            email.isEmpty() -> UserValidationDataDomainModel(
                isValid = false,
                errorMessage = context.getString(TextFieldErrors.requiredFieldErrorId)
            )

            Patterns.EMAIL_ADDRESS.matcher(email).matches() -> UserValidationDataDomainModel(
                isValid = true,
                errorMessage = null
            )

            else -> {
                UserValidationDataDomainModel(
                    isValid = false,
                    errorMessage = context.getString(TextFieldErrors.emailErrorId)
                )
            }
        }
    }
}