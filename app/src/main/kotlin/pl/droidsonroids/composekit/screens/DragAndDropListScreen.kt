package pl.droidsonroids.composekit.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.droidsonroids.composekit.composables.dragdroplist.DragAndDropList

@Composable
fun DragAndDropListScreen() {
    DragAndDropList(
        modifier = Modifier.fillMaxSize(),
        items = List(40) { it },
        onDragEnd = { _ -> /* do something with the reordered list*/ },
        itemContent = { item, isDragging ->
            val elevation by animateDpAsState(
                targetValue = if (isDragging) 4.dp else 1.dp,
                label = "Card elevation animation",
            )
            Card(elevation = CardDefaults.cardElevation(defaultElevation = elevation)) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    text = "Item $item"
                )
            }
        }
    )
}
