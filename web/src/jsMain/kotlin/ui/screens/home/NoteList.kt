package ui.screens.home

import androidx.compose.runtime.Composable
import data.Notes
import data.TypeNotes
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import ui.common.Icon

@Composable
fun NotesList(notes: List<Notes>, onNoteClick: (Notes) -> Unit) {
    Div(
        attrs = {
            // classes(AppStyleSheet.noteList)
            id("noteList")
        }
    ) {
        notes.forEach { note ->
            NoteCard(note, onNoteClick)
        }
    }
}

@Composable
fun NoteCard(note: Notes, onNoteClick: (Notes) -> Unit) {
    Div(
        attrs = {
            onClick { onNoteClick(note) }
            //classes(AppStyleSheet.noteCard)
            id("noteCard")
        }
    ) {
        Div(
            attrs = {
                //  classes(AppStyleSheet.noteCardHeader)
                id("noteCardHeader")
            }
        ) {
            H3(
                attrs = {
                    // classes(AppStyleSheet.noteCardTitle)
                    id("noteCardTitle")
                }
            ) { Text(note.title) }
            if (note.type == TypeNotes.AUDIO_NOTE) {
                Icon("volume_up")
            }
        }
        Div { Text(note.description) }
    }
}