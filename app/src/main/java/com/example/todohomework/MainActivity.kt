package com.example.todohomework
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.todohomework.nav.AppNavigation
import com.example.todohomework.ui.theme.ToDoHomeworkTheme
import com.example.todohomework.viewModels.NoteViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoHomeworkTheme {
                val navController = rememberNavController()
                val viewModel: NoteViewModel = viewModel()
                val notes = viewModel.notes.collectAsStateWithLifecycle().value


                AppNavigation(navController = navController, viewModel = viewModel)

            }
        }
    }
}
