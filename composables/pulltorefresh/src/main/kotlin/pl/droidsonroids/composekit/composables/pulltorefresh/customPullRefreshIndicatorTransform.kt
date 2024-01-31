@file:Suppress("Filename")

package pl.droidsonroids.composekit.composables.pulltorefresh

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.Dp

/*
 * The implementation is based on the pullRefreshIndicatorTransform created by Google.
 * Introduced changes:
 *  - adjusted calculations in graphicsLayer {}
 *
 * When making any changes here, double-check the original implementation.
 */
@ExperimentalMaterialApi
internal fun Modifier.customPullRefreshIndicatorTransform(
    state: PullRefreshState,
    refreshing: Boolean,
    topSpacing: Dp,
) = this.composed(
    inspectorInfo = debugInspectorInfo {
        name = "customPullRefreshIndicatorTransform"
        properties["state"] = state
        properties["refreshing"] = refreshing
    }
) {
    var height by remember { mutableStateOf(0) }
    Modifier
        .onSizeChanged { height = it.height }
        .graphicsLayer {
            val topSpacingInPx = topSpacing.toPx()
            if (!refreshing) {
                val percent = minOf(state.progress, 1f)
                translationY = percent * height - topSpacingInPx

                val scaleFraction = LinearOutSlowInEasing
                    .transform(percent)
                    .coerceIn(0f, 1f)
                scaleX = scaleFraction
                scaleY = scaleFraction
            } else {
                translationY = height - topSpacingInPx
            }
        }
}