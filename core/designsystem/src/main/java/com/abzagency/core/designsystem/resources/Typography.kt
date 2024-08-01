package com.abzagency.core.designsystem.resources

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.abzagency.core.designsystem.R

object Typography {
    val header = TextStyle(
        fontFamily = FontFamily(Font(R.font.nutino_sans_semi_bold)),
        fontSize = 28.0.sp,
        lineHeight = 32.0.sp,
        letterSpacing = 1.5.sp
    )

    val h1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.nutino_sans_regular)),
        fontSize = 20.0.sp,
        fontWeight = FontWeight(400),
        lineHeight = 24.0.sp
    )

    val bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.nutino_sans_regular)),
        fontSize = 16.0.sp,
        fontWeight = FontWeight(600),
        lineHeight = 24.0.sp,
        letterSpacing = 0.1.sp
    )

    val body2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.nutino_sans_regular)),
        fontSize = 18.0.sp,
        fontWeight = FontWeight(400),
        lineHeight = 24.0.sp
    )

    val body3 = TextStyle(
        fontFamily = FontFamily(Font(R.font.nutino_sans_regular)),
        fontSize = 14.0.sp,
        fontWeight = FontWeight(400),
        lineHeight = 20.0.sp
    )
}