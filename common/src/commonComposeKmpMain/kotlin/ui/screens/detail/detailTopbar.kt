package ui.screens.detail

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import data.Notes
import data.TypeAction.*
import viewmodel.DetailViewModel

@Composable
fun detailTopbar(viewModel: DetailViewModel) {

    val note = viewModel.stateNote.note

    TopAppBar(
        title = { Text(text = note.title) },
        navigationIcon = {
            IconButton(onClick = { viewModel.onAction(CLOSE) }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Navigation")
            }
        },
        actions = {
            IconButton(onClick = { viewModel.onAction(SAVE) }) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save Note")
            }
            if (note.id != Notes.NEW_NOTE) {
                IconButton(
                    onClick = { viewModel.onAction(DELETE) }
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Note")
                }
            }
        })
}