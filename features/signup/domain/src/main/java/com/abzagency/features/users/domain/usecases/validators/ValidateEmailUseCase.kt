package com.abzagency.features.users.domain.usecases.validators

import android.content.Context
import com.abzagency.core.designsystem.ui.textfield.TextFieldErrors
import com.abzagency.features.users.models.domain.UserValidationDataDomainModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.regex.Pattern
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor(
    @ApplicationContext private val context: Context
) {
    operator fun invoke(email: String?): UserValidationDataDomainModel {
        val emailPattern = Pattern.compile(EMAIL_PATTERN)

        return when {
            email.isNullOrEmpty() -> UserValidationDataDomainModel(
                isValid = false,
                errorMessage = context.getString(TextFieldErrors.requiredFieldErrorId)
            )

            emailPattern.matcher(email).matches() -> UserValidationDataDomainModel(
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

    companion object {
        private const val EMAIL_PATTERN =
            "^[a-zA-Z0-9.!#\$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$"
    }
}