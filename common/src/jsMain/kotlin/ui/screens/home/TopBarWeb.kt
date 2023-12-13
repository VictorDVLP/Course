package ui.screens.home

import androidx.compose.runtime.*
import data.Filter
import data.TypeNotes
import getAppTitle
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text
import ui.common.Icon
import ui.theme.AppStyleSheet

@Composable
fun TopBar(onFilteredClick: (Filter) -> Unit) {
    Div(
        attrs = {
            classes(AppStyleSheet.topBar)
        }
    ) {
        H1(
            attrs = {
                classes(AppStyleSheet.topBarTitle)
            }
        ) { Text(getAppTitle()) }
        FiltersAction(onFilteredClick)
    }
}

@Composable
fun FiltersAction(onFilteredClick: (Filter) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Div(
        attrs = {
            classes(AppStyleSheet.filtersAction)
        }
    ) {
        Div(
            attrs = {
                style { color(Color.white) }
                onClick { expanded = true }
            }
        ) {
            Icon("filter_list")
        }
        if (expanded) {
            Div(
                attrs = {
                    classes(AppStyleSheet.filtersActionExpanded)
                }
            ) {
                listOf(
                    Filter.NotesAll to "All",
                    Filter.NotesFilter(type = TypeNotes.WRITTEN_NOTE) to "Text",
                    Filter.NotesFilter(type = TypeNotes.AUDIO_NOTE) to "Audio"
                ).forEach { (filter, text) ->
                    Div(
                        attrs = {
                            classes(AppStyleSheet.filtersActionExpandedItem)
                            onClick {
                                onFilteredClick(filter)
                                expanded = false
                            }
                        }
                    ) {
                        Text(text)
                    }
                }
            }
        }
    }
}
