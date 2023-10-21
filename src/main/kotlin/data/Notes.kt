package data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class Notes(val title: String, val description: String, val type: TypeNotes)
enum class TypeNotes {
    WRITTEN_NOTE,
    AUDIO_NOTE
}

fun getNotes(): Flow<List<Notes>> =
    flow {
        delay(2000)
        val notes = (1..10).map {
            Notes(
                "Title $it",
                "Description $it",
                if (it % 3 == 0) TypeNotes.AUDIO_NOTE else TypeNotes.WRITTEN_NOTE
            )
        }
        emit(notes)
    }




