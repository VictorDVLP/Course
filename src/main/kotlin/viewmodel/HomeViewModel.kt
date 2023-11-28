package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import data.Filter
import data.UiState
import data.remote.NotesRepository
import data.remote.notesClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class HomeViewModel( private val scope: CoroutineScope) {

    var state by mutableStateOf(UiState())
        private set

    init {
        loadNotes()
    }

//    private val _state = MutableStateFlow(UiState())
//    val state = _state.asStateFlow()

    // Custom delegate usage example

    //  var state by MutableStateFlow { UiState() }
    //      private set

   private fun loadNotes() {
        scope.launch {
            state = UiState(loading = true)
            val response = NotesRepository.getAllNotes()
            state = UiState(notes = response, loading = false)
//            _state.value = UiState(loading = true)
//            getNotes().collect {
//                _state.value = UiState(notes = it, loading = false)
//            }
        }
    }


    fun filterNotes(filter: Filter) {
        state = state.copy( filter = filter )
        }
    }



