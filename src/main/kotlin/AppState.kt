import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.concurrent.thread

object AppState {
    var state by mutableStateOf(UiState())

    fun loadNotes() {
        thread {
            state = UiState(loading = true)
            getNotes {
                state = UiState(notes = it, loading = false)
            }
        }
    }
}


