package com.example.todohomework.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.todohomework.data.Note
import com.example.todohomework.viewModels.NoteViewModel

@Composable
fun EditNoteScreen(
    note: Note,
    viewModel: NoteViewModel,
    onNoteUpdated: () -> Unit
) {
    var title by remember { mutableStateOf(TextFieldValue(note.title)) }
    var content by remember { mutableStateOf(TextFieldValue(note.content)) }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Заголовок") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Текст") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (title.text.isNotBlank() && content.text.isNotBlank()) {
                        val updatedNote = note.copy(
                            title = title.text,
                            content = content.text
                        )
                        viewModel.updateNote(updatedNote)
                        onNoteUpdated()
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Зберегти")
            }
        }
    }
}
