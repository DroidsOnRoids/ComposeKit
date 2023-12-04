package com.dor.compose.playground.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dor.compose.playground.composables.button.ScrollableWithFoldableToolbar

@Composable
fun FoldableToolbarScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        ScrollableWithFoldableToolbar(
            isToolbarVisible = true,
            toolbarHeight = 56.dp,
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
}
