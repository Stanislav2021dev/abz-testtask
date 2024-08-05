package com.abzagency.core.designsystem.ui.textfield

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.text.input.VisualTransformation
import com.abzagency.core.designsystem.resources.Colors
import com.abzagency.core.designsystem.resources.Dimens
import com.abzagency.core.designsystem.resources.Typography
import com.abzagency.core.designsystem.resources.errorColor
import com.abzagency.core.designsystem.resources.textPrimary
import com.abzagency.core.designsystem.resources.textSecondary
import com.abzagency.core.designsystem.ui.common.AnimatedErrorText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    value: String,
    hint: String,
    onValueChange: (value: String) -> Unit,
    singleLine: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    error: String?
) {
    val colors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = if (error != null) Colors.errorColor() else Colors.textPrimary(),
        unfocusedTextColor = if (error != null) Colors.errorColor() else Colors.textSecondary(),
        errorTextColor = Colors.errorColor(),
        cursorColor = Colors.textPrimary(),
        errorBorderColor = Colors.errorColor(),
        errorCursorColor = Colors.errorColor(),
        errorPlaceholderColor = Colors.errorColor(),
        focusedBorderColor = Colors.textPrimary(),
        unfocusedBorderColor = Colors.textSecondary()
    )

    val isFocused = remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        BasicTextField(
            value = value,
            modifier = modifier
                .fillMaxWidth()
                .height(Dimens.textFieldHeight)
                .onFocusEvent { event ->
                    isFocused.value = event.isFocused
                },
            onValueChange = onValueChange,
            textStyle =  when {
                error != null -> Typography.bodyLarge.copy(
                    color = Colors.errorColor()
                )
                !isFocused.value -> Typography.bodyLarge.copy(
                    color = Colors.textSecondary()
                )
                else ->Typography.bodyLarge.copy(
                    color = Colors.textPrimary()
                )
            },
            keyboardOptions = keyboardOptions,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            decorationBox = @Composable { innerTextField ->
                OutlinedTextFieldDefaults.DecorationBox(
                    value = value,
                    visualTransformation = VisualTransformation.None,
                    innerTextField = innerTextField,
                    placeholder = {
                        Text(
                            text = hint,
                            color = Colors.textSecondary(),
                            style = Typography.bodyLarge
                        )
                    },
                    singleLine = singleLine,
                    enabled = true,
                    isError = error != null,
                    interactionSource = interactionSource,
                    contentPadding = PaddingValues(
                        horizontal = Dimens.spacingNormal,
                        vertical = Dimens.spacingNormal
                    ),
                    colors = colors,
                    container = {
                        OutlinedTextFieldDefaults.ContainerBox(
                            enabled = true,
                            isError = error != null,
                            interactionSource = interactionSource,
                            colors = colors,
                            shape = RoundedCornerShape(Dimens.shapesNormal),
                            focusedBorderThickness = Dimens.textFieldBorderHeight,
                            unfocusedBorderThickness = Dimens.textFieldBorderHeight
                        )
                    }
                )
            }
        )

        Spacer(modifier = Modifier.height(Dimens.spacingTiny))

        error?.let { error ->
            AnimatedErrorText(error = error)
        } ?: run {
            Text(
                text = "",
                style = Typography.bodySmall
            )
        }
    }
}