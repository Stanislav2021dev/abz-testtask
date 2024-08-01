package com.abzagency.features.users.presentation.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.SubcomposeAsyncImage
import com.abzagency.core.designsystem.resources.Colors
import com.abzagency.core.designsystem.resources.Dimens
import com.abzagency.core.designsystem.resources.Typography
import com.abzagency.core.designsystem.resources.backgroundPrimary
import com.abzagency.core.designsystem.resources.dividerColor
import com.abzagency.core.designsystem.resources.textPrimary
import com.abzagency.core.designsystem.resources.textSecondary
import com.abzagency.features.users.models.presentation.UserPresentationModel
import com.abzagency.features.users.presentation.dimens.UsersDimens

@Composable
fun UserItem(
    modifier: Modifier,
    user: UserPresentationModel
) {
    Column(modifier = modifier) {
        Row {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .size(UsersDimens.userAvatarSize)
                    .clip(CircleShape),
                model = user.photo,
                contentDescription = user.name,
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(Dimens.spacingNormal))
            
            Column {
                Text(
                    text = user.name,
                    color = Colors.textPrimary(),
                    style = Typography.body2
                )

                Spacer(modifier = Modifier.height(Dimens.spacingTiny))

                Text(
                    text = user.position,
                    color = Colors.textSecondary(),
                    style = Typography.body3
                )

                Spacer(modifier = Modifier.height(Dimens.spacingSmall))

                Text(
                    text = user.email,
                    color = Colors.textPrimary(),
                    style = Typography.body3
                )

                Spacer(modifier = Modifier.height(Dimens.spacingTiny))

                Text(
                    text = user.phone,
                    color = Colors.textPrimary(),
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

@Composable
@Preview
fun UserItemPreview() {
    UserItem(
        modifier = Modifier.background(Colors.backgroundPrimary()),
        user = UserPresentationModel(
            id = 0,
            name = "Test name",
            position = "Android",
            photo = "",
            email = "qwerty@gmail.com",
            phone = "12345678"
        )
    )
}