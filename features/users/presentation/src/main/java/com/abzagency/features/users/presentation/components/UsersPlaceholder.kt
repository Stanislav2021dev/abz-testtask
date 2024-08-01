package com.abzagency.features.users.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.abzagency.core.designsystem.resources.Colors
import com.abzagency.core.designsystem.resources.Dimens
import com.abzagency.core.designsystem.resources.Typography
import com.abzagency.core.designsystem.resources.backgroundPrimary
import com.abzagency.core.designsystem.resources.textPrimary
import com.abzagency.features.users.presentation.R

@Composable
fun UsersPlaceholder() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.backgroundPrimary()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(
            id = R.drawable.ic_success_image),
            contentDescription = stringResource(id = R.string.empty_state_title)
        )
        
        Spacer(modifier = Modifier.height(Dimens.spacingBig))

        Text(
            text = stringResource(id = R.string.empty_state_title),
            color = Colors.textPrimary(),
            style = Typography.h1
        )
    }
}

@Composable
@Preview
fun UsersPlaceholderPreview() {
    UsersPlaceholder()
}