package com.abzagency.core.designsystem.resources

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.abzagency.core.designsystem.R

object Typography {
    val bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.nutino_sans)),
        fontSize = 16.0.sp,
        fontWeight = FontWeight(600),
        lineHeight = 24.0.sp,
        letterSpacing = 0.1.sp
    )
}