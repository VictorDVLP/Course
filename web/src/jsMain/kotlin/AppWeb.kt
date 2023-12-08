import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import navigation.Route
import ui.screens.detail.DetailScreenWeb
import ui.screens.home.HomeScreenWeb
import viewmodel.DetailViewModel
import viewmodel.HomeViewModel

@Composable
fun AppWeb() {

    var routeWeb by rememberSaveable { mutableStateOf<Route>(Route.Home) }
    val scopeWeb = rememberCoroutineScope()

    routeWeb.let { road ->
        when (road) {
            Route.Home -> HomeScreenWeb(
                viewModel = HomeViewModel(scope = scopeWeb),
                onNoteClick = { routeWeb = Route.Detail(it) }
            )

            is Route.Detail -> DetailScreenWeb(
                viewModel = DetailViewModel(scope = scopeWeb, noteId = road.id),
                onAction = { routeWeb = Route.Home }
            )
        }
    }
}