package com.example.todohomework.viewModels

import androidx.lifecycle.ViewModel
import com.example.todohomework.data.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.StateFlow


class NoteViewModel : ViewModel() {
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes.asStateFlow()

    private var nextId = 1

    fun addNote(title: String, content: String) {
        if (title.isBlank() || content.isBlank()) return
        val newNote = Note(id = nextId++, title = title, content = content)
        _notes.value = _notes.value + newNote
    }
}


