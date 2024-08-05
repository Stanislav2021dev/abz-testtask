package com.abzagency.features.splash.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.abzagency.core.designsystem.resources.Colors
import com.abzagency.core.designsystem.resources.Dimens
import com.abzagency.core.designsystem.resources.Typography
import com.abzagency.core.designsystem.resources.primary
import com.abzagency.core.designsystem.resources.textPrimary
import com.abzagency.features.splash.presentation.R

@Composable
internal fun SplashRoute() {
    SplashScreen()
}

@Composable
internal fun SplashScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.primary()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.zIndex(1f),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = stringResource(id = R.string.spalsh_logo_content_description)
        )

        Spacer(modifier = Modifier.height(Dimens.spacingNormal))

        Text(
            text = stringResource(id = R.string.splash_title),
            color = Colors.textPrimary(),
            style = Typography.header
        )
    }
}

@Composable
@Preview
private fun SplashScreenPreview() {
    SplashScreen()
}