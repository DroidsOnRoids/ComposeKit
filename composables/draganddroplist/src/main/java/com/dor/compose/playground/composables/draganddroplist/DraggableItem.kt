package com.dor.compose.playground.composables.draganddroplist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.zIndex

@ExperimentalFoundationApi
@Composable
internal fun LazyItemScope.DraggableItem(
    modifier: Modifier = Modifier,
    dragDropState: DragDropState,
    index: Int,
    content: @Composable ColumnScope.(isDragging: Boolean) -> Unit,
) {
    val dragging = dragDropState.isDraggingItem(index)
    val draggingModifier = if (dragging) {
        Modifier
            .zIndex(1f)
            .graphicsLayer { translationY = dragDropState.draggingItemOffset }
    } else {
        Modifier.animateItemPlacement()
    }
    Column(modifier = modifier.then(draggingModifier)) {
        content(dragging)
    }
}
