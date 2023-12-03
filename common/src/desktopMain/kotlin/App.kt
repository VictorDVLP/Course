import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import navigation.Route
import ui.detail.detailScreen
import ui.home.HomeScreen
import viewmodel.DetailViewModel
import viewmodel.HomeViewModel

@Composable
@Preview
fun App() {

    var route by rememberSaveable { mutableStateOf<Route>(Route.Home) }
    val scope = rememberCoroutineScope()

    route.let { road ->
        when (road) {
            Route.Home -> HomeScreen(
                viewModel = HomeViewModel(scope = scope),
                onNoteClick = { route = Route.Detail(it) }
            )
            is Route.Detail -> detailScreen(
                viewModel = DetailViewModel(scope = scope, noteId = road.id),
                onAction = { route = Route.Home }
            )
        }
    }
}