package com.abzagency.features.signup.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.abzagency.core.common.constants.Constants
import com.abzagency.core.designsystem.resources.Colors
import com.abzagency.core.designsystem.resources.Dimens
import com.abzagency.core.designsystem.resources.Typography
import com.abzagency.core.designsystem.resources.errorColor
import com.abzagency.core.designsystem.resources.secondary
import com.abzagency.core.designsystem.resources.textSecondary
import com.abzagency.core.designsystem.ui.common.AnimatedErrorText
import com.abzagency.features.signup.presentation.R
import com.abzagency.features.signup.presentation.dimens.SignUpDimens
import java.io.File

@Composable
fun ImagePickerContainer(
    photo: File?,
    uploadPhoto: () -> Unit,
    clearPhoto: () -> Unit,
    error: String?
) {
    val contentColor = if (error == null) {
        Colors.textSecondary()
    } else {
        Colors.errorColor()
    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = Dimens.dividerHeight,
                    shape = RoundedCornerShape(Dimens.shapesNormal),
                    color = contentColor
                )
                .padding(horizontal = Dimens.spacingNormal),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (photo != null) {
                AsyncImage(
                    modifier = Modifier
                        .padding(vertical = Dimens.spacingSmall)
                        .clip(CircleShape)
                        .size(SignUpDimens.avatarSize),
                    contentScale = ContentScale.Crop,
                    model = photo,
                    contentDescription = stringResource(id = R.string.photo_picker_title)
                )
            } else {
                Text(
                    modifier = Modifier.padding(vertical = Dimens.spacingNormal),
                    text = stringResource(id = R.string.upload_photo),
                    color = contentColor,
                    style = Typography.bodyLarge
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(Dimens.shapesNormal))
                    .clickable(
                        onClick = {
                            if (photo != null) {
                                clearPhoto()
                            } else {
                                uploadPhoto()
                            }
                        }
                    )
                    .padding(
                        horizontal = Dimens.spacingNormal,
                        vertical = Dimens.spacingSmall
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(
                        id = (if (photo != null) {
                            R.string.clear_photo_btn_text
                        } else {
                            R.string.upload_photo_btn_text
                        })
                    ),
                    color = Colors.secondary(),
                    style = Typography.body1
                )
            }
        }

        Spacer(modifier = Modifier.height(Dimens.spacingTiny))

        error?.let { error ->
            AnimatedErrorText(error = error)
        } ?: run {
            Text(
                text = Constants.EMPTY_STRING,
                style = Typography.bodySmall
            )
        }
    }
}