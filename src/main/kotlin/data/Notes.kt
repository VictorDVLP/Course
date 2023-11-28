package data

import kotlinx.serialization.Serializable

@Serializable
data class Notes(val id: Long = NEW_NOTE, val title: String, val description: String, val type: TypeNotes) {
    companion object {
        const val NEW_NOTE = -1L
    }
}

enum class TypeNotes {
    WRITTEN_NOTE,
    AUDIO_NOTE
}

enum class TypeAction {
    CLOSE,
    SAVE,
    DELETE
}

//fun getNotes(): Flow<List<Notes>> =
//    flow {
//        delay(2000)
//        val notes = (1..10).map {
//            Notes(
//                "Title $it",
//                "Description $it",
//                if (it % 3 == 0) TypeNotes.AUDIO_NOTE else TypeNotes.WRITTEN_NOTE
//            )
//        }
//        emit(notes)
//    }




