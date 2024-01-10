package pl.droidsonroids.composekit.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material3.Text
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import pl.droidsonroids.composekit.composables.pulltorefresh.CustomPullRefreshIndicator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun CustomPullRefreshScreen() {
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }
    var itemCount by remember { mutableStateOf(15) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        delay(1500)
        itemCount += 5
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)
    val paddingTop by remember {
        derivedStateOf {
            if (refreshing) {
                PullRefreshHeight
            } else {
                PullRefreshHeight * minOf(state.progress, 1f)
            }
        }
    }

    val animatedPaddingTop by animateDpAsState(paddingTop)

    Box(Modifier.pullRefresh(state)) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = animatedPaddingTop)
        ) {
            items(itemCount) {
                ListItem { Text(text = "Item ${itemCount - it}") }
            }
        }

        CustomPullRefreshIndicator(Modifier.align(Alignment.TopCenter), refreshing, state)
    }
}

private val PullRefreshHeight = 80.dp