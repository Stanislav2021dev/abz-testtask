package com.abzagency.features.signup.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abzagency.core.designsystem.resources.Colors
import com.abzagency.core.designsystem.resources.Dimens
import com.abzagency.core.designsystem.resources.Typography
import com.abzagency.core.designsystem.resources.secondary
import com.abzagency.core.designsystem.resources.textPrimary
import com.abzagency.features.signup.presentation.dimens.SignUpDimens
import com.abzagency.features.signup.models.presentation.PositionPresentationModel

@Composable
fun PositionContainer(
    position: PositionPresentationModel,
    currentSelectedPositionId: Int,
    onPositionSelect: (positionId: Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            modifier = Modifier
                .padding(SignUpDimens.radioButtonPadding)
                .size(Dimens.spacingBigSpecial),
            colors = RadioButtonDefaults.colors().copy(
                selectedColor = Colors.secondary(),
                unselectedColor = Colors.secondary()
            ),
            selected = currentSelectedPositionId == position.id,
            onClick = {
                onPositionSelect(position.id)
            }
        )

        Spacer(modifier = Modifier.width(Dimens.spacingSmall))

        Text(
            text = position.name,
            color = Colors.textPrimary(),
            style = Typography.body1
        )
    }
}