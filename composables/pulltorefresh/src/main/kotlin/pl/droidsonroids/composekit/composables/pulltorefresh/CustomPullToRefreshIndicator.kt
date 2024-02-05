package pl.droidsonroids.composekit.composables.pulltorefresh

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

/**
 * The implementation is based on the PullRefreshIndicator created by Google.
 * Introduced changes:
 *  - Removed the shape and the background
 *  - Bigger size
 *  - Custom implementation of PullRefreshIndicatorTransform
 *
 * When making any changes here, double-check the original implementation.
 */

@ExperimentalMaterialApi
@Composable
fun CustomPullRefreshIndicator(
    modifier: Modifier = Modifier,
    refreshing: Boolean,
    state: PullRefreshState,
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor),
) {
    Surface(
        modifier = modifier
            .size(IndicatorSize)
            .customPullRefreshIndicatorTransform(
                state = state,
                refreshing = refreshing,
                topSpacing = IndicatorSize / 2
            )
    ) {
        Crossfade(
            targetState = refreshing,
            animationSpec = tween(durationMillis = CROSS_FADE_DURATION_MS)
        ) { refreshing ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                val spinnerSize = (ArcRadius + StrokeWidth).times(2)

                if (refreshing) {
                    CircularProgressIndicator(
                        color = contentColor,
                        strokeWidth = StrokeWidth,
                        modifier = Modifier.size(spinnerSize),
                    )
                } else {
                    CircularArrowIndicator(
                        state = state,
                        color = contentColor,
                        modifier = Modifier.size(spinnerSize)
                    )
                }
            }
        }
    }
}

/**
 * Modifier.size MUST be specified.
 */
@Composable
@ExperimentalMaterialApi
private fun CircularArrowIndicator(
    modifier: Modifier,
    state: PullRefreshState,
    color: Color,
) {
    val path = remember { Path().apply { fillType = PathFillType.EvenOdd } }

    Canvas(modifier.semantics { contentDescription = "Refreshing" }) {
        val values = ArrowValues(minOf(1f, state.progress))

        rotate(degrees = values.rotation) {
            val arcRadius = ArcRadius.toPx() + StrokeWidth.toPx() / 2f
            val arcBounds = Rect(
                size.center.x - arcRadius,
                size.center.y - arcRadius,
                size.center.x + arcRadius,
                size.center.y + arcRadius
            )
            drawArc(
                color = color,
                alpha = values.alpha,
                startAngle = values.startAngle,
                sweepAngle = values.endAngle - values.startAngle,
                useCenter = false,
                topLeft = arcBounds.topLeft,
                size = arcBounds.size,
                style = Stroke(
                    width = StrokeWidth.toPx(),
                    cap = StrokeCap.Square
                )
            )
            drawArrow(path, arcBounds, color, values)
        }
    }
}

@Immutable
private class ArrowValues(
    val alpha: Float,
    val rotation: Float,
    val startAngle: Float,
    val endAngle: Float,
    val scale: Float,
)

private fun ArrowValues(progress: Float): ArrowValues {
    // Discard first 40% of progress. Scale remaining progress to full range between 0 and 100%.
    val adjustedPercent = max(min(1f, progress) - 0.4f, 0f) * 5 / 3
    // How far beyond the threshold pull has gone, as a percentage of the threshold.
    val overshootPercent = abs(progress) - 1.0f
    // Limit the overshoot to 200%. Linear between 0 and 200.
    val linearTension = overshootPercent.coerceIn(0f, 2f)
    // Non-linear tension. Increases with linearTension, but at a decreasing rate.
    val tensionPercent = linearTension - linearTension.pow(2) / 4

    // Calculations based on SwipeRefreshLayout specification.
    val alpha = progress.coerceIn(0f, 1f)
    val endTrim = adjustedPercent * MAX_PROGRESS_ARC
    val rotation = (-0.25f + 0.4f * adjustedPercent + tensionPercent) * 0.5f
    val startAngle = rotation * 360
    val endAngle = (rotation + endTrim) * 360
    val scale = min(1f, adjustedPercent)

    return ArrowValues(alpha, rotation, startAngle, endAngle, scale)
}

private fun DrawScope.drawArrow(arrow: Path, bounds: Rect, color: Color, values: ArrowValues) {
    arrow.reset()
    arrow.moveTo(0f, 0f) // Move to left corner
    arrow.lineTo(x = ArrowWidth.toPx() * values.scale, y = 0f) // Line to right corner

    // Line to tip of arrow
    arrow.lineTo(
        x = ArrowWidth.toPx() * values.scale / 2,
        y = ArrowHeight.toPx() * values.scale
    )

    val radius = min(bounds.width, bounds.height) / 2f
    val inset = ArrowWidth.toPx() * values.scale / 2f
    arrow.translate(
        Offset(
            x = radius + bounds.center.x - inset,
            y = bounds.center.y + StrokeWidth.toPx() / 2f
        )
    )
    arrow.close()
    rotate(degrees = values.endAngle) {
        drawPath(path = arrow, color = color, alpha = values.alpha)
    }
}

private const val CROSS_FADE_DURATION_MS = 100
private const val MAX_PROGRESS_ARC = 0.75f

private val IndicatorSize = 40.dp
private val ArcRadius = 15.dp
private val StrokeWidth = 3.dp
private val ArrowWidth = 10.dp
private val ArrowHeight = 5.dp