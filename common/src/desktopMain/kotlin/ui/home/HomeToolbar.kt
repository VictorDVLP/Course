package ui.home

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import data.*
import getAppTitle

@Composable
fun HomeToolbar(onFilterClick: (Filter) -> Unit) {

    TopAppBar(
        title = { Text(text = getAppTitle()) },
        actions = { FilterIcons(onFilterClick = onFilterClick)
    })
}

@Composable
private fun FilterIcons(onFilterClick: (Filter) -> Unit) {

    var expanded by rememberSaveable { mutableStateOf(false) }

    IconButton(
        onClick = { expanded = true }
    ) {
        Icon(
            imageVector = Icons.Default.FilterList, contentDescription = "Filter Notes"
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {

            @Composable
            infix fun Filter.ToMenuItem(text: String) = DropdownMenuItem(onClick = {
                expanded = false
                onFilterClick(this)
            }) {
                Text(text = text)
            }

            Filter.NotesAll ToMenuItem "All"
            Filter.NotesFilter(type = TypeNotes.AUDIO_NOTE) ToMenuItem "Audio"
            Filter.NotesFilter(type = TypeNotes.WRITTEN_NOTE) ToMenuItem "Text"

         //   listOf(
         //       (Filter.NotesAll to "All"),
         //       (Filter.NotesFilter(type = TypeNotes.AUDIO_NOTE) to "Audio"),
         //       (Filter.NotesFilter(type = TypeNotes.WRITTEN_NOTE) to "Text")
         //   ).forEach { (filter, text) ->
         //       DropdownMenuItem(onClick = {
         //           expanded = false
         //           onFilterClick(filter)
         //       }) {
         //           Text(text = text)
         //       }
         //   }
        }
    }
}
