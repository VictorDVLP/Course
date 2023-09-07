data class Notes(val title: String, val description: String, val type: TypeNotes)
enum class TypeNotes {
    WRITTEN_NOTE,
    AUDIO_NOTE
}


fun getNotes(callBack: (List<Notes>) -> Unit) {
    Thread.sleep(2000)
    val notes =
        (1..10).map {
            Notes(
                "Title $it",
                "Description $it",
                if (it % 3 == 0) TypeNotes.AUDIO_NOTE else TypeNotes.WRITTEN_NOTE
            )
        }
    callBack(notes)
}




