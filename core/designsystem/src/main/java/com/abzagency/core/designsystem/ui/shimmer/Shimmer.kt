package com.abzagency.core.designsystem.ui.shimmer

import android.annotation.SuppressLint
import androidx.annotation.FloatRange
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.abzagency.core.designsystem.resources.Dimens
import com.eygraber.compose.placeholder.PlaceholderDefaults
import com.eygraber.compose.placeholder.PlaceholderHighlight
import com.eygraber.compose.placeholder.placeholder
import kotlin.math.max

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.shimmer(
    backgroundColor: Color = Color(0xFFDADADA),
    highlightColor: Color = Color(0xFFFFFFFF),
    shape: Shape = RoundedCornerShape(Dimens.spacingTiny)
) = placeholder(
    visible = true,
    color = backgroundColor,
    highlight = PlaceholderHighlight.linearGradientShimmer(highlightColor),
    shape = shape
)

fun PlaceholderHighlight.Companion.linearGradientShimmer(
    highlightColor: Color,
    animationSpec: InfiniteRepeatableSpec<Float> = PlaceholderDefaults.shimmerAnimationSpec,
    @FloatRange(from = 0.0, to = 1.0) progressForMaxAlpha: Float = 0.6f
): PlaceholderHighlight = Shimmer(
    highlightColor = highlightColor,
    animationSpec = animationSpec,
    progressForMaxAlpha = progressForMaxAlpha
)

private data class Shimmer(
    private val highlightColor: Color,
    override val animationSpec: InfiniteRepeatableSpec<Float>,
    private val progressForMaxAlpha: Float = 0.6f
) : PlaceholderHighlight {
    override fun brush(progress: Float, size: Size): Brush {
        val max = max(size.width, size.height)
        val delta = 2 * max * progress
        return Brush.linearGradient(
            0.1f to highlightColor.copy(alpha = 0f),
            0.5f to highlightColor,
            0.9f to highlightColor.copy(alpha = 0f),
            start = Offset(x = -max + delta, y = 0f + delta),
            end = Offset(x = 0f + delta, y = 0f + delta)
        )
    }

    override fun alpha(progress: Float): Float = progressForMaxAlpha
}