package com.abzagency.features.signup.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.abzagency.core.designsystem.resources.Dimens
import com.abzagency.core.designsystem.resources.Typography
import com.abzagency.core.designsystem.ui.shimmer.shimmer
import com.abzagency.features.signup.presentation.dimens.SignUpDimens

@Composable
fun PositionShimmers() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(SignUpDimens.radioButtonPadding)
                .size(Dimens.spacingBigSpecial)
                .clip(CircleShape)
                .shimmer(shape = CircleShape)
        )

        Spacer(modifier = Modifier.width(Dimens.spacingSmall))

        Text(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .shimmer(),
            text = "Shimmer position",
            style = Typography.body1
        )
    }
}