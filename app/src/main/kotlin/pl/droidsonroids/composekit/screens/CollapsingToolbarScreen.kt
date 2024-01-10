@file:OptIn(ExperimentalMaterial3Api::class)

package pl.droidsonroids.composekit.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pl.droidsonroids.composekit.R
import pl.droidsonroids.composekit.composables.theme.MainTheme
import pl.droidsonroids.composekit.composables.collapsingtoolbar.ParallaxAppBar

@Composable
fun CollapsingToolbarScreen() {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        topBar = {
            ParallaxAppBar(
                title = {
                    Text(text = "Collapsing toolbar")
                },
                scrollBehavior = scrollBehavior,
                expandedBackground = {
                    Image(
                        painter = painterResource(id = R.drawable.cover),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                            .nestedScroll(scrollBehavior.nestedScrollConnection)
                    )
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
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