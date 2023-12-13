package ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ui.screens.detail.Detail
import viewmodel.HomeViewModel

object Home: Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        HomeScreen(
            viewModel = rememberScreenModel { HomeViewModel() },
            onNoteClick = { navigator.push(Detail(it)) }
        )
    }
}

@Composable
expect fun HomeScreen(viewModel: HomeViewModel, onNoteClick: (Long) -> Unit)