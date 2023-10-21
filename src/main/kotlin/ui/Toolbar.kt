package ui

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.runtime.*

@Composable
fun Toolbar() {
    TopAppBar(
        title = { Text(text = "My notes") },
        actions = {
            FilterIcons()
        }
    )
}

@Composable
private fun FilterIcons() {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Default.FilterList,
            contentDescription = "Filter Notes"
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }) {
            DropdownMenuItem(onClick = { expanded = false }) {
                Text(text = "All")
            }
            DropdownMenuItem(onClick = { expanded = false }) {
                Text(text = "Audio")
            }
            DropdownMenuItem(onClick = { expanded = false }) {
                Text(text = "Text")
            }
        }
    }
}
