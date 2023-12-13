package ui.screens.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.TypeAction
import data.TypeAction.*
import data.TypeNotes
import viewmodel.DetailViewModel

@Composable
actual fun detailScreen(viewModel: DetailViewModel, onAction: (TypeAction) -> Unit) {

    val note = viewModel.stateNote.note

    Scaffold(
        topBar = { detailTopbar(viewModel = viewModel) }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            if (viewModel.stateNote.saved) {
                onAction(CLOSE)
            }

            if (viewModel.stateNote.loading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                Column {
                    OutlinedTextField(
                        value = note.title,
                        onValueChange = { viewModel.updateNote(note.copy(title = it)) },
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        label = { Text("Title") },
                        maxLines = 1
                    )

                    typeDropDown(
                        value = note.type,
                        onValueChange = { viewModel.updateNote(note.copy(type = it)) }
                    )

                    OutlinedTextField(
                        value = note.description,
                        onValueChange = { viewModel.updateNote(note.copy(description = it)) },
                        modifier = Modifier.fillMaxWidth().weight(1f).padding(16.dp),
                        label = { Text("Description") }
                    )
                }
            }
        }
    }
}

@Composable
private fun typeDropDown(value: TypeNotes, onValueChange: (TypeNotes) -> Unit) {

    var expanded by rememberSaveable { mutableStateOf(false) }

    Box(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = value.toString(),
            onValueChange = { },
            readOnly = true,
            label = { Text("Type") },
            trailingIcon = {
                IconButton(
                    onClick = { expanded = true }) {
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Show note type")
                }
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            TypeNotes.entries.forEach {
                DropdownMenuItem(
                    onClick = {
                        onValueChange(it)
                        expanded = false
                    }
                ) {
                    Text(it.name)
                }
            }
        }
    }
}