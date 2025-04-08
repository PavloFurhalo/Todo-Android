package com.example.todohomework.repository
import com.example.todohomework.data.Note
import com.example.todohomework.data.NoteDao
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val dao: NoteDao) {
    val notes: Flow<List<Note>> = dao.getAllNotes()
    suspend fun insert(note: Note) = dao.insertNote(note)
    suspend fun update(note: Note) = dao.updateNote(note)
    suspend fun delete(note: Note) = dao.deleteNote(note)
}
