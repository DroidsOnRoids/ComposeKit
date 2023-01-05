import com.ramcosta.composedestinations.annotation.NavGraph

@NavGraph(default = true)
internal annotation class ComposeDestinationsLibraryNavGraph(
    val start: Boolean = false
)
