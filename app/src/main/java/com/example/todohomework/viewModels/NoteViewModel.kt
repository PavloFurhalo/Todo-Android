package com.example.todohomework.viewModels
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todohomework.data.MyRoomDatabase
import com.example.todohomework.data.Note
import com.example.todohomework.repository.NoteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = MyRoomDatabase.getDatabase(application).noteDao()
    private val repo = NoteRepository(dao)
    val notes = repo.notes.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun addNote(title: String, content: String) {
        viewModelScope.launch {
            repo.insert(Note(title = title, content = content))
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch { repo.delete(note) }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch { repo.update(note) }
    }
}
