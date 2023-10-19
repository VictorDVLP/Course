import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

object AppState {
    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()


    fun loadNotes(coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            _state.value = UiState(loading = true)
            getNotes().collect {
                _state.value = UiState(notes = it, loading = false)}
        }
    }
}


