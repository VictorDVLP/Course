package ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Mic
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import viewmodel.HomeViewModel
import data.Notes
import data.TypeNotes

@Composable
fun HomeScreen(viewModel: HomeViewModel, onNoteClick: (noteId: Long) -> Unit) {

    MaterialTheme {
        Scaffold(
            topBar = { HomeToolbar(onFilterClick = viewModel::filterNotes) },
            floatingActionButton = {
                FloatingActionButton(onClick = { onNoteClick(Notes.NEW_NOTE) }) {
                    Icon(
                        imageVector = Icons.Default.Add, contentDescription = "Add Note"
                    )
                }
            }
        ) { padding ->
            Box(
                modifier = Modifier.fillMaxSize().padding(padding)
            ) {
                if (viewModel.state.loading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                viewModel.state.filteredNotes?.let { listNotes ->
                    notesList(notes = listNotes, onNoteClick = { onNoteClick(it.id) })
                }
            }
        }
    }

}


@Composable
private fun notesList(notes: List<Notes>, onNoteClick: (Notes) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(notes) {
            Card(
                modifier = Modifier.padding(8.dp).fillMaxWidth(0.8f).clickable { onNoteClick(it) }
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row {
                        Text(
                            text = it.title, style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f)
                        )
                        if (it.type == TypeNotes.AUDIO_NOTE) {
                            Icon(
                                imageVector = Icons.Default.Mic, contentDescription = null
                            )
                        }
                    }
                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )
                    Text(
                        text = it.description, style = MaterialTheme.typography.body1
                    )
                }
            }
        }

    }
}