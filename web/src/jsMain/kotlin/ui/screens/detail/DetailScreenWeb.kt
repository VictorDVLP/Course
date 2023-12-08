package ui.screens.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import data.TypeAction
import data.TypeAction.*
import data.TypeNotes
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.attributes.selected
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import ui.theme.AppStyleSheet
import viewmodel.DetailViewModel

@Composable
fun DetailScreenWeb(viewModel: DetailViewModel, onAction: (TypeAction) -> Unit) {

    val note = viewModel.stateNote.note

    Div {
        detailToolbarWeb(
            note = note,
            onAction = viewModel::onAction
        )
        if (viewModel.stateNote.saved) {
            onAction(CLOSE)
        }

        if (viewModel.stateNote.loading) {
            Text("Loading...")
        } else {
            Div(
                attrs = {
                    style {
                        display(DisplayStyle.Flex)
                        padding(32.px)
                        flexDirection(FlexDirection.Column)
                        gap(16.px)
                        maxWidth(600.px)
                        property("margin", "0 auto")
                    }
                }
            ) {
                TextInput(
                    value = note.title,
                    attrs = {
                        classes(AppStyleSheet.detailImput)
                        placeholder("Title")
                        onInput { viewModel.updateNote(note.copy(title = it.value)) }
                    }
                )
                typeDropDown(
                    value = note.type,
                    onValueChange = { viewModel.updateNote(note.copy(type = it)) }
                )
                TextArea(
                    value = note.description,
                    attrs = {
                        classes(AppStyleSheet.detailImput)
                        placeholder("Description")
                        onInput { viewModel.updateNote(note.copy(description = it.value)) }
                    }
                )
            }
        }
    }
}

@Composable
private fun typeDropDown(value: TypeNotes, onValueChange: (TypeNotes) -> Unit) {
    Select(
        attrs = {
            classes(AppStyleSheet.detailImput)
            onChange{ onValueChange(TypeNotes.valueOf(it.target.value)) }
        }
    ) {
        TypeNotes.entries.forEach {
            Option(
                value = it.name,
                attrs = {
                    if (it == value) selected()
                }
            ) {
                Text(it.name)
            }
            H1(
                attrs = {
                    classes(AppStyleSheet.topBarTitle)
                }
            ) {
                Text(it.name)
            }
        }
    }
}