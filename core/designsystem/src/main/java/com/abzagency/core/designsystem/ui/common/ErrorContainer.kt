package com.abzagency.core.designsystem.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.abzagency.core.common.response.ErrorCodes.INTERNET_CONNECTION_ERROR
import com.abzagency.core.common.response.ErrorData
import com.abzagency.core.designsystem.resources.Colors
import com.abzagency.core.designsystem.resources.backgroundPrimary
import com.abzagency.core.designsystem.R
import com.abzagency.core.designsystem.resources.Dimens
import com.abzagency.core.designsystem.resources.Typography
import com.abzagency.core.designsystem.resources.textPrimary

@Composable
fun ErrorContainer(
    errorData: ErrorData?,
    onCloseClick: () -> Unit,
    onRetryClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.backgroundPrimary())
            .systemBarsPadding()
            .padding(Dimens.spacingBig),
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .clickable(onClick = onCloseClick)
                .padding(Dimens.spacingTiny)
                .align(Alignment.TopEnd),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = stringResource(id = R.string.close_btn_content_description)
            )
        }

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(
                    id = if (errorData?.code == INTERNET_CONNECTION_ERROR) {
                        R.drawable.ic_no_internet
                    } else {
                        R.drawable.ic_error
                    }
                ),
                contentDescription = errorData?.message
            )

            Spacer(modifier = Modifier.height(Dimens.spacingBig))

            Text(
                text = errorData?.messageResourceId?.let { stringResource(id = it) }
                    ?: errorData?.message.toString(),
                style = Typography.h1,
                color = Colors.textPrimary(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(Dimens.spacingBig))

            CommonButton(
                text = stringResource(id = R.string.retry_btn_text),
                onClick = onRetryClick
            )
        }
    }
}