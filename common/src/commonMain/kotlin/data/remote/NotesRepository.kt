package data.remote

import data.Notes
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

expect val NOTES_URL: String

object NotesRepository {

    suspend fun saveNote(notes: Notes) {
        notesClient.post(urlString = NOTES_URL) {
            setBody(notes)
            contentType(ContentType.Application.Json)
        }
    }

    suspend fun getAllNotes(): List<Notes> {
        val response = notesClient.request(
            urlString = NOTES_URL
        )
        return response.body()
    }

    suspend fun getById(id: Long): Notes {
        val response = notesClient.request(
            urlString = "$NOTES_URL/$id"
        )
        return response.body()
    }

    suspend fun updateNote(notes: Notes) {
        notesClient.put(urlString = NOTES_URL) {
            setBody(notes)
            contentType(ContentType.Application.Json)
        }
    }

    suspend fun deleteNote(note: Notes) {
        notesClient.delete("$NOTES_URL/${note.id}") {

        }
    }
}