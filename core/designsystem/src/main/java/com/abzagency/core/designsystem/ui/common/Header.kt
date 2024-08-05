package com.abzagency.core.designsystem.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.abzagency.core.designsystem.resources.Colors
import com.abzagency.core.designsystem.resources.Dimens
import com.abzagency.core.designsystem.resources.Typography
import com.abzagency.core.designsystem.resources.primary
import com.abzagency.core.designsystem.resources.textPrimary

@Composable
fun Header(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.headerHeight)
            .background(Colors.primary()),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = Colors.textPrimary(),
            style = Typography.h1
        )
    }
}