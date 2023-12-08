package data

sealed interface Filter {
    object NotesAll : Filter
    class NotesFilter(val type: TypeNotes) : Filter
}