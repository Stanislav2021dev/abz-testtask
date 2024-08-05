package com.abzagency.testtask.com.abzagency.testtask.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun TestTaskApplicationTheme(content: @Composable () -> Unit) {
    val statusBarColor = Color.Transparent.toArgb()

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val currentWindow = (view.context as? Activity)?.window
                ?: throw Exception("Not in an activity - unable to get Window reference")

            WindowCompat.getInsetsController(currentWindow, view).isAppearanceLightStatusBars =
                true

            currentWindow.statusBarColor = statusBarColor
        }
    }

    MaterialTheme(
        content = content
    )
}