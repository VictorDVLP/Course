package ui.screens.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.core.screen.Screen
import data.TypeAction
import viewmodel.DetailViewModel

data class Detail( val noteId: Long): Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        detailScreen(
            viewModel = rememberScreenModel { DetailViewModel(noteId) },
            onAction = { navigator.pop() }
        )
    }
}

@Composable
expect fun detailScreen(viewModel: DetailViewModel, onAction: (TypeAction) -> Unit)