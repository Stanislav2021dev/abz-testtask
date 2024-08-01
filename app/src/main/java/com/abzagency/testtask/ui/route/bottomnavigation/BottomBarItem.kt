package com.abzagency.testtask.com.abzagency.testtask.ui.route.bottomnavigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.abzagency.core.designsystem.resources.Colors
import com.abzagency.core.designsystem.resources.Dimens
import com.abzagency.core.designsystem.resources.Typography
import com.abzagency.core.designsystem.resources.secondary
import com.abzagency.core.designsystem.resources.textSecondary

@Composable
fun BottomBarItem(
    modifier: Modifier,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    icon: @Composable (tint: Color) -> Unit
) {
    val itemsColor = if (isSelected) {
        Colors.secondary()
    } else {
        Colors.textSecondary()
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(Dimens.spacingSmall))
                .clickable(onClick = onClick)
                .padding(Dimens.spacingTiny),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon(itemsColor)

            Spacer(modifier = Modifier.width(Dimens.spacingSmall))

            Text(
                text = text,
                style = Typography.bodyLarge,
                color = itemsColor
            )
        }
    }
}