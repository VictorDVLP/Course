import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlin.concurrent.thread

class AppState {
    val state: MutableState<UiState> = mutableStateOf(UiState())

    fun loadNotes() {
        thread {
            state.update { it.copy(loading = true) }
            getNotes { notes ->
                state.update { it.copy(notes = notes, loading = false) }
            }
        }
    }
}


