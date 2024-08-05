package com.abzagency.features.signup.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
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
import com.abzagency.core.designsystem.ui.common.CommonButton
import com.abzagency.features.signup.presentation.R

@Composable
fun SignupSuccessContainer(
    onProceedClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.backgroundPrimary())
            .systemBarsPadding()
            .padding(Dimens.spacingBig),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.ic_success),
            contentDescription = stringResource(id = R.string.success_title)
        )

        Spacer(modifier = Modifier.height(Dimens.spacingBig))

        Text(
            text = stringResource(id = R.string.success_title),
            style = Typography.h1,
            color = Colors.textPrimary(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(Dimens.spacingBig))

        CommonButton(
            text = stringResource(id = R.string.success_proceed_btn_text),
            onClick = onProceedClick
        )
    }
}