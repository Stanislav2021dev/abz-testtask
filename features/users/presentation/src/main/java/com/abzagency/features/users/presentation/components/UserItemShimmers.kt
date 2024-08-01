package com.abzagency.features.users.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.abzagency.core.designsystem.resources.Colors
import com.abzagency.core.designsystem.resources.Dimens
import com.abzagency.core.designsystem.resources.Typography
import com.abzagency.core.designsystem.resources.dividerColor
import com.abzagency.core.designsystem.resources.textSecondary
import com.abzagency.core.designsystem.ui.shimmer.shimmer
import com.abzagency.features.users.presentation.dimens.UsersDimens

@Composable
fun UserItemShimmers(
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Row {
            Box(
                modifier = Modifier
                    .size(UsersDimens.userAvatarSize)
                    .clip(CircleShape)
                    .shimmer()
            )

            Spacer(modifier = Modifier.width(Dimens.spacingNormal))

            Column {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .shimmer(),
                    text = "Name shimmers",
                    style = Typography.body2
                )

                Spacer(modifier = Modifier.height(Dimens.spacingTiny))

                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .shimmer(),
                    text = "Position shimmers",
                    color = Colors.textSecondary(),
                    style = Typography.body3
                )

                Spacer(modifier = Modifier.height(Dimens.spacingSmall))

                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .shimmer(),
                    text = "shimmers Email",
                    style = Typography.body3
                )

                Spacer(modifier = Modifier.height(Dimens.spacingTiny))

                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .shimmer(),
                    text = "phone shimmers",
                    style = Typography.body3
                )

                Spacer(modifier = Modifier.height(Dimens.spacingBig))

                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = Dimens.dividerHeight,
                    color = Colors.dividerColor()
                )
            }
        }
    }
}