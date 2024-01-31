package pl.droidsonroids.composekit.composables.foldabletoolbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import pl.droidsonroids.composekit.composables.utils.toDp
import kotlin.math.roundToInt

private val TOOLBAR_HEIGHT = 56.dp

/**
 * Creates a scrollable layout with a foldable toolbar effect.
 *
 * @param modifier The modifier to apply to the entire composable.
 * @param isToolbarVisible Flag indicating whether the toolbar is initially visible.
 * @param toolbarHeight The height of the toolbar.
 * @param topBar The composable representing the top bar content.
 * @param content The composable representing the scrollable content.
 */
@Composable
fun ScrollableWithFoldableToolbar(
    modifier: Modifier = Modifier,
    isToolbarVisible: Boolean = true,
    toolbarHeight: Dp,
    topBar: @Composable (Modifier) -> Unit,
    content: @Composable (Modifier) -> Unit,
) {
    val topBarHeightPx = with(LocalDensity.current) { toolbarHeight.roundToPx().toFloat() }
    var topBarOffsetHeightPx by remember { mutableStateOf(0f) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                if (available.x != 0f) return Offset.Zero
                val delta = available.y
                val newOffset = topBarOffsetHeightPx + delta
                topBarOffsetHeightPx = newOffset.coerceIn(-topBarHeightPx, 0f)

                return if (newOffset in (-topBarHeightPx..0f)) available else Offset.Zero
            }
        }
    }

    Scaffold(
        modifier = modifier
            .nestedScroll(nestedScrollConnection),
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            if (isToolbarVisible) {
                topBar(Modifier.offset { IntOffset(x = 0, y = topBarOffsetHeightPx.roundToInt()) })
            }
            val offset = if (isToolbarVisible) toolbarHeight + topBarOffsetHeightPx.toDp() else 0.dp
            content(
                Modifier.padding(top = offset.coerceAtLeast(0.dp))
            )
        }
    }
}

@Composable
@Preview
@OptIn(ExperimentalMaterial3Api::class)
private fun ProductListWithViewTypeChange(
    modifier: Modifier = Modifier,
) {
    ScrollableWithFoldableToolbar(
        modifier = modifier,
        isToolbarVisible = true,
        toolbarHeight = TOOLBAR_HEIGHT,
        topBar = { topBarModifier ->
            TopAppBar(
                modifier = topBarModifier,
                title = { Text(text = "Test list") },
            )
        }
    ) { topBarModifier ->
        LazyColumn(
            modifier = topBarModifier.fillMaxSize()
        ) {
            items(100) { index ->
                Text(text = "$index: Lorem ipsum", modifier = Modifier.padding(16.dp))
            }
        }
    }
}