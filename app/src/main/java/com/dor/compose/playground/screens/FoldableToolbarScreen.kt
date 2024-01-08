package com.dor.compose.playground.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dor.compose.playground.composables.foldabletoolbar.ScrollableWithFoldableToolbar
import com.dor.compose.playground.composables.theme.MainTheme

private val TOOLBAR_HEIGHT = 72.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoldableToolbarScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        ScrollableWithFoldableToolbar(
            isToolbarVisible = true,
            toolbarHeight = TOOLBAR_HEIGHT,
            topBar = { modifier ->
                TopAppBar(
                    modifier = modifier.height(TOOLBAR_HEIGHT),
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MainTheme.colors.onSurface,
                    ),
                    title = { TopAppBarTitle() },
                )
            }
        ) { modifier ->
            LazyColumn(
                modifier = modifier.fillMaxSize()
            ) {
                items(100) { index ->
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "$index: Lorem ipsum",
                        style = MainTheme.typography.bodyLarge,
                    )
                }
            }
        }
    }
}

@Composable
private fun TopAppBarTitle() {
    Box(
        modifier = Modifier.fillMaxHeight(),
        contentAlignment = Alignment.CenterStart,
    ) {
        Text(
            text = "Collapsing toolbar",
            style = MainTheme.typography.titleLarge,
            color = MainTheme.colors.surface,
        )
    }
}
