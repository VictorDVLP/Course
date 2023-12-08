package ui.screens.home

import androidx.compose.runtime.Composable
import data.Notes
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import ui.common.Icon
import ui.theme.AppStyleSheet
import viewmodel.HomeViewModel

@Composable
fun HomeScreenWeb(viewModel: HomeViewModel, onNoteClick: (noteId: Long) -> Unit) {

    Div(
        attrs = {
            style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)
                width(100.percent)
                height(100.percent)
            }
        }
    ) {
        TopBar( onFilteredClick = viewModel::filterNotes )

        Div {
            if(viewModel.state.loading) {
                Icon("refresh")
            }

            viewModel.state.filteredNotes?.let { notes ->
                NotesList(
                    notes = notes,
                    onNoteClick = {onNoteClick(it.id)}
                )
            }
        }

        Div(
            attrs = {
                classes(AppStyleSheet.fab)
                onClick { onNoteClick(Notes.NEW_NOTE) }
            }
        ) {
            Icon(
                iconName = "add"
            )
        }
    }
}
