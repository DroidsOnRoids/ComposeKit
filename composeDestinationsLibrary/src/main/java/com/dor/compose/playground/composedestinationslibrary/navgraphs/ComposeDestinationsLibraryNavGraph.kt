package com.dor.compose.playground.composedestinationslibrary.navgraphs

import com.ramcosta.composedestinations.annotation.NavGraph

@NavGraph(default = true)
internal annotation class ComposeDestinationsLibraryNavGraph(
    val start: Boolean = false
)
