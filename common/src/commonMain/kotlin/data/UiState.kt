package data

data class UiState(
    val notes: List<Notes>? = null,
    val loading: Boolean = false,
    val filter: Filter = Filter.NotesAll
) {
    val filteredNotes get() = when (filter) {
        is Filter.NotesAll -> notes
        is Filter.NotesFilter -> notes?.filter { it.type == filter.type }
    }
}
