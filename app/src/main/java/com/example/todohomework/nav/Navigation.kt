package com.example.todohomework.nav


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todohomework.screens.AddNoteScreen
import com.example.todohomework.screens.MainScreen
import com.example.todohomework.screens.NoteDetailScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todohomework.viewModels.NoteViewModel
import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun AppNavigation(navController: NavHostController, viewModel: NoteViewModel = viewModel()) {
    val notes by viewModel.notes.collectAsStateWithLifecycle()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            MainScreen(
                onAddNoteClick = { navController.navigate("add_note") },
                onNoteClick = { note -> navController.navigate("note_detail/${note.id}") },
                viewModel = viewModel
            )
        }
        composable("add_note") {
            AddNoteScreen(
                onNoteSaved = { navController.popBackStack() },
                viewModel = viewModel
            )
        }
        composable("note_detail/{noteId}") { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")?.toIntOrNull()
            val note = notes.find { it.id == noteId }

            if (note != null) {
                NoteDetailScreen(note = note, onBack = { navController.popBackStack() })
            }
        }
    }
}
