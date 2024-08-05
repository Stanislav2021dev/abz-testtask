package com.abzagency.features.signup.domain.usecases.validators

import android.content.Context
import android.graphics.BitmapFactory
import com.abzagency.core.designsystem.ui.textfield.TextFieldErrors
import com.abzagency.features.signup.models.domain.UserValidationDataDomainModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject

class ValidatePhotoUseCase @Inject constructor(
    @ApplicationContext private val context: Context
) {
    operator fun invoke(photo: File?): UserValidationDataDomainModel {
        val options = BitmapFactory.Options().apply {
            inJustDecodeBounds = true
        }
        BitmapFactory.decodeFile(photo?.absolutePath, options)

        return when {
            photo == null -> UserValidationDataDomainModel(
                isValid = false,
                errorMessage = context.getString(TextFieldErrors.requiredFieldErrorId)
            )

            photo.length() > MAX_PHOTO_SIZE -> UserValidationDataDomainModel(
                isValid = false,
                errorMessage = context.getString(TextFieldErrors.photoIsBigErrorId)
            )

            options.outWidth < MIN_PHOTO_WIDTH_PX && options.outHeight < MIN_PHOTO_HEIGHT_PX -> {
                UserValidationDataDomainModel(
                    isValid = false,
                    errorMessage = context.getString(TextFieldErrors.photoIsSmallErrorId)
                )
            }

            else -> UserValidationDataDomainModel(
                isValid = true,
                errorMessage = null
            )
        }
    }

    companion object {
        private const val MAX_PHOTO_SIZE = 5 * 1024 * 1024
        private const val MIN_PHOTO_WIDTH_PX = 70
        private const val MIN_PHOTO_HEIGHT_PX = 70
    }
}