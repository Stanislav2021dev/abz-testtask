package com.abzagency.features.signup.domain.usecases.validators

import android.content.Context
import com.abzagency.core.designsystem.ui.textfield.TextFieldErrors
import com.abzagency.features.signup.models.domain.UserValidationDataDomainModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.regex.Pattern
import javax.inject.Inject

class ValidatePhoneUseCase @Inject constructor(
    @ApplicationContext private val context: Context
) {
    operator fun invoke(phone: String): UserValidationDataDomainModel {
        val emailPattern = Pattern.compile(PHONE_NUMBER_PATTERN)

        return when {
            phone.isEmpty() -> UserValidationDataDomainModel(
                isValid = false,
                errorMessage = context.getString(TextFieldErrors.requiredFieldErrorId)
            )

            emailPattern.matcher(phone).matches() -> UserValidationDataDomainModel(
                isValid = true,
                errorMessage = null
            )

            else -> {
                UserValidationDataDomainModel(
                    isValid = false,
                    errorMessage = context.getString(TextFieldErrors.phoneErrorId)
                )
            }
        }
    }

    companion object {
        private const val PHONE_NUMBER_PATTERN = "^\\+380[0-9]{9}$"
    }
}
    val phoneNumberPattern = Pattern.compile("^\\+380[0-9]{9}$")

    // Check if the phone number matches the pattern
  //  return phoneNumberPattern.matcher(phoneNumber).matches()

