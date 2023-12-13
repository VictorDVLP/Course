package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.Filter
import data.UiState
import data.remote.NotesRepository
import kotlinx.coroutines.launch

class HomeViewModel: ScreenModel {

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
        screenModelScope.launch {
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



