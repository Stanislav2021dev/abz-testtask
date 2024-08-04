package com.abzagency.core.designsystem.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import com.abzagency.core.designsystem.resources.Colors
import com.abzagency.core.designsystem.resources.Typography
import com.abzagency.core.designsystem.resources.errorColor

@Composable
fun AnimatedErrorText(error: String?) {
    val isVisible = remember(error) {
        derivedStateOf { !error.isNullOrEmpty() }
    }
    AnimatedVisibility(
        visible = isVisible.value,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        Text(
            text = error.orEmpty(),
            style = Typography.bodySmall,
            color = Colors.errorColor()
        )
    }
}