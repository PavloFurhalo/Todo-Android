package com.example.todohomework.screens
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todohomework.data.Note
import com.example.todohomework.viewModels.NoteViewModel

@Composable
fun MainScreen(
    onAddNoteClick: () -> Unit,
    onNoteClick: (Note) -> Unit,
    viewModel: NoteViewModel = viewModel()
) {
    val notes by viewModel.notes.collectAsState()

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Add a note") },
                onClick = onAddNoteClick,
                icon = { Icon(Icons.Default.Add, contentDescription = "Add") }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            if (notes.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                    Text("No notes yet")
                }
            } else {
                LazyColumn {
                    items(notes) { note ->
                        NoteItem(note = note, onClick = { onNoteClick(note) }, onLongClick = {viewModel.deleteNote(note)})
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteItem(note: Note, onClick: () -> Unit, onLongClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = note.content,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
