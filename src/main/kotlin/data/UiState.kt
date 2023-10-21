package data

import data.Notes

data class UiState(
    var notes: List<Notes>? = null,
    var loading: Boolean = false
)
