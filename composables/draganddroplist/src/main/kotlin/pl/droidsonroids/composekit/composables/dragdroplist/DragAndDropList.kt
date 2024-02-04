package pl.droidsonroids.composekit.composables.dragdroplist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

/**
 * @param modifier the modifier to apply to the lazy column.
 * @param items items to display in a list.
 * @param contentPadding a padding around the whole lazy column content.
 * @param verticalArrangement the vertical arrangement of the lazy column items.
 * @param itemContent a block which describes the item content.
 * @param onDragEnd will be called when the user completes the drag gesture.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> DragAndDropList(
    items: List<T>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(16.dp),
    onDragEnd: (reorderedList: List<T>) -> Unit,
    itemContent: @Composable (item: T, isDragging: Boolean) -> Unit,
) {
    var list by rememberSaveable { mutableStateOf(items) }
    val listState = rememberLazyListState()
    val dragDropState = rememberDragDropState(
        lazyListState = listState,
        onDragEnd = { onDragEnd(list) },
        onMove = { fromIndex, toIndex -> list = list.withReplacedAt(fromIndex, toIndex) }
    )

    LazyColumn(
        modifier = modifier.dragContainer(dragDropState = dragDropState),
        state = listState,
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement
    ) {
        itemsIndexed(list, key = { _, item -> item.hashCode() }) { index, item ->
            DraggableItem(
                dragDropState = dragDropState,
                index = index
            ) { isDragging ->
                itemContent(item, isDragging)
            }
        }
    }
}

@Composable
private fun rememberDragDropState(
    lazyListState: LazyListState,
    onMove: (Int, Int) -> Unit,
    onDragEnd: () -> Unit,
): DragDropState {
    val scope = rememberCoroutineScope()
    val state = remember(lazyListState) {
        DragDropState(
            state = lazyListState,
            onMove = onMove,
            onDragEnd = onDragEnd,
            scope = scope
        )
    }
    LaunchedEffect(state) {
        while (true) {
            val diff = state.scrollChannel.receive()
            lazyListState.scrollBy(diff)
        }
    }
    return state
}

private fun <T> List<T>.withReplacedAt(fromIndex: Int, toIndex: Int) =
    toMutableList().apply { add(toIndex, removeAt(fromIndex)) }

private fun Modifier.dragContainer(dragDropState: DragDropState) = then(
    pointerInput(dragDropState) {
        detectDragGesturesAfterLongPress(
            onDrag = { _, offset -> dragDropState.onDrag(offset) },
            onDragStart = { offset -> dragDropState.onDragStart(offset) },
            onDragEnd = { dragDropState.onDragInterrupted() },
            onDragCancel = { dragDropState.onDragInterrupted() }
        )
    }
)
