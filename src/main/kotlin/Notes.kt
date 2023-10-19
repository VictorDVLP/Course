import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class Notes(val title: String, val description: String, val type: TypeNotes)
enum class TypeNotes {
    WRITTEN_NOTE,
    AUDIO_NOTE
}

suspend fun getNotes(): Flow<List<Notes>> =
    flow {
        delay(2000)
        var notes = emptyList<Notes>()
        (1..10).forEach {
            notes = notes +
                Notes(
                    "Title $it",
                    "Description $it",
                    if (it % 3 == 0) TypeNotes.AUDIO_NOTE else TypeNotes.WRITTEN_NOTE
                )
            emit(notes)
            delay(500)
        }
    }




