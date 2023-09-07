import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.skia.impl.Log

@Composable
private fun notesList(notes: List<Notes>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(notes) {
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(0.8f)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Row {
                        Text(
                            text = it.title,
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.weight(1f)
                        )
                        if (it.type == TypeNotes.AUDIO_NOTE) {
                            Icon(
                                imageVector = Icons.Default.Mic,
                                contentDescription = null
                            )
                        }
                    }
                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )
                    Text(
                        text = it.description,
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }

    }
}

@Composable
@Preview
fun App(appState: AppState) {

    val newNotes= appState.state.value.notes

    if (newNotes == null) {
        LaunchedEffect(true) {
            appState.loadNotes()
        }
    }
    Log.info(s = "El valor de notes es $newNotes")
    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (appState.state.value.loading) CircularProgressIndicator()
             if (newNotes != null) notesList(newNotes)
        }
    }
}

fun main() {
    val state = AppState()

    application {
        Window(onCloseRequest = ::exitApplication) {
            App(state)
        }
    }
}
