package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import data.Notes
import data.TypeAction
import data.TypeAction.*
import data.TypeNotes.WRITTEN_NOTE
import data.remote.NotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DetailViewModel(private val scope: CoroutineScope, private val noteId: Long) {

    var stateNote by mutableStateOf(UiState())
        private set

    init {
        if (noteId != Notes.NEW_NOTE) {
            loadNote()
        }
    }
    private fun loadNote() {
        scope.launch {
            stateNote = UiState(loading = true)
            val note = NotesRepository.getById(noteId)
            stateNote = UiState(note = note, loading = false)
        }
    }

    private fun saveNote() {
        scope.launch {
            val note = stateNote.note
            if (note.id == Notes.NEW_NOTE) {
                NotesRepository.saveNote(note)
            }else{
                NotesRepository.updateNote(note)
            }
            stateNote = stateNote.copy(saved = true)
        }
    }

    fun updateNote( note: Notes) {
        stateNote = stateNote.copy(note = note)
    }

    private fun deleteNote() {
        scope.launch {
            val note = stateNote.note
            NotesRepository.deleteNote(note)
            stateNote = stateNote.copy(saved = true)
        }
    }

    data class UiState(
        val note: Notes = Notes(title = "", description = "",  type = WRITTEN_NOTE),
        val loading: Boolean = false,
        val saved: Boolean = false
    )

    fun onAction(action: TypeAction) {
        when (action) {
            SAVE -> saveNote()
            DELETE -> deleteNote()
            CLOSE -> stateNote = stateNote.copy(saved = true)
        }
    }

}