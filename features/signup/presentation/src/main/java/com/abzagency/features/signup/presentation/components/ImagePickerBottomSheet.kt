package com.abzagency.features.signup.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.abzagency.core.designsystem.resources.Colors
import com.abzagency.core.designsystem.resources.Dimens
import com.abzagency.core.designsystem.resources.Typography
import com.abzagency.core.designsystem.resources.backgroundPrimary
import com.abzagency.core.designsystem.resources.textPrimary
import com.abzagency.core.designsystem.resources.textSecondary
import com.abzagency.features.signup.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImagePickerBottomSheet(
    onDismiss: () -> Unit,
    onCameraClick: () -> Unit,
    onGalleryClick: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Colors.backgroundPrimary(),
        shape = RoundedCornerShape(
            topEnd = Dimens.bottomSheetCornerRadius,
            topStart = Dimens.bottomSheetCornerRadius,
            bottomStart = Dimens.zeroDp,
            bottomEnd = Dimens.zeroDp
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(bottom = Dimens.spacingLarge),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.photo_picker_title),
                color = Colors.textSecondary(),
                style = Typography.bodyLarge,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(Dimens.spacingLarge))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                SourcePickItem(
                    title = stringResource(id = R.string.camera),
                    imageResourceId = R.drawable.ic_camera,
                    onClick = onCameraClick
                )

                SourcePickItem(
                    title = stringResource(id = R.string.gallery),
                    imageResourceId = R.drawable.ic_gallery,
                    onClick = onGalleryClick
                )
            }
        }
    }
}

@Composable
private fun SourcePickItem(
    title: String,
    imageResourceId: Int,
    onClick: () -> Unit
) {
    Column {
        Image(
            modifier = Modifier.clickable(
                onClick = onClick
            ),
            painter = painterResource(id = imageResourceId),
            contentDescription = title
        )

        Spacer(modifier = Modifier.height(Dimens.spacingNormalSpecial))

        Text(
            text = title,
            color = Colors.textPrimary(),
            style = Typography.bodyLarge
        )
    }
}