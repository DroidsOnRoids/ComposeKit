package com.dor.compose.playground.composables.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
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
import androidx.compose.ui.unit.dp
import com.dor.compose.playground.composables.utils.toDp
import kotlin.math.roundToInt

private val TOOLBAR_HEIGHT = 56.dp

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
            content(
                Modifier.padding(top = if (isToolbarVisible) toolbarHeight + topBarOffsetHeightPx.toDp() else 0.dp)
            )
        }
    }
}

@Composable
@Preview
private fun ProductListWithViewTypeChange(
    modifier: Modifier = Modifier,
) {
    ScrollableWithFoldableToolbar(
        modifier = modifier,
        isToolbarVisible = true,
        toolbarHeight = TOOLBAR_HEIGHT,
        topBar = { modifier ->
            TopAppBar(
                modifier = modifier,
                title = { Text(text = "Test list") },
            )
        }
    ) { modifier ->
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            items(100) { index ->
                Text(text = "$index: Lorem ipsum", modifier = Modifier.padding(16.dp))
            }
        }
    }
}