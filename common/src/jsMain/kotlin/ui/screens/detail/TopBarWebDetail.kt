package ui.screens.detail

import androidx.compose.runtime.Composable
import data.Notes
import data.TypeAction
import data.TypeAction.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text
import ui.common.Icon
import ui.theme.AppStyleSheet

@Composable
fun detailToolbarWeb(note: Notes, onAction: (TypeAction) -> Unit) {

    Div(attrs = {
        classes(AppStyleSheet.topBar)
    }) {
        Icon(
            iconName = "arrow_back",
            attrs = {
                classes(AppStyleSheet.topBarIcon)
                onClick { onAction(CLOSE) }
            }
        )
        H1(attrs = {
            classes(AppStyleSheet.topBarTitle)
        }) {
            Text(note.title)
        }

        Icon(
            iconName = "save",
            attrs = {
                classes(AppStyleSheet.topBarIcon)
                onClick { onAction(SAVE) }
            }
        )

        if (note.id != Notes.NEW_NOTE) {
            Icon(
                iconName = "delete",
                attrs = {
                    classes(AppStyleSheet.topBarIcon)
                    onClick { onAction(DELETE) }
                }
            )
        }
    }
}