package com.dor.compose.playground.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dor.compose.playground.composables.draganddroplist.DragAndDropList

@Composable
fun DragAndDropListScreen() {
    DragAndDropList(
        modifier = Modifier.fillMaxSize(),
        items = List(40) { it },
        onDragEnd = { _ -> /* do something with the reordered list*/ },
        itemContent = { item, isDragging ->
            val elevation by animateDpAsState(if (isDragging) 4.dp else 1.dp)
            Card(elevation = elevation) {
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
