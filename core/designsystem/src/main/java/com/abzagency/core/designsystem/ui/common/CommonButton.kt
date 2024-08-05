package com.abzagency.core.designsystem.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.abzagency.core.designsystem.resources.Colors
import com.abzagency.core.designsystem.resources.Dimens
import com.abzagency.core.designsystem.resources.Typography
import com.abzagency.core.designsystem.resources.primary
import com.abzagency.core.designsystem.resources.textPrimary

@Composable
fun CommonButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.width(Dimens.buttonWidth),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = Colors.primary()
        )
    ) {
        Text(
            modifier = Modifier.padding(vertical = Dimens.spacingNormalSpecial),
            text = text,
            color = Colors.textPrimary(),
            style = Typography.body1SemiBold
        )
    }
}