package com.musscoding.noteit

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.musscoding.noteit.presentation.edit_note.EditNote
import com.musscoding.noteit.presentation.main_screen.MainScreen
import com.musscoding.noteit.ui.theme.NoteItTheme
import dagger.hilt.android.AndroidEntryPoint
import util.fabActions
import kotlin.system.exitProcess

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteItTheme {
                val navHostController = rememberNavController()
                val context = LocalContext.current
                NavHost(
                    navController = navHostController,
                    startDestination = Route.MAIN_NOTES_VIEW
                ) {
                    composable(Route.MAIN_NOTES_VIEW) {
                        MainScreen(
                            onNoteCreateClick = {
                                navHostController.navigate(Route.NOTE_EDIT)
                            }
                        )
                    }
                    composable(Route.NOTE_EDIT) {
                        EditNote(
                            onBackClicked = {
                                navHostController.navigateUp()
                            }
                        )
                        //Log.d("MA", "My Current Dest: ${navHostController.currentDestination?.route}")
                    }
                }
                var currentTime = 0L
                val exitThreshold = 3_000
                onBackPressedDispatcher.addCallback(
                    this,
                    object : OnBackPressedCallback(true) {
                        override fun handleOnBackPressed() {
                            if (navHostController.currentDestination?.route == Route.NOTE_EDIT) {
                                navHostController.navigateUp()
                            } else {
                                val elapsedTime = System.currentTimeMillis() - currentTime
                                if (elapsedTime > exitThreshold) {
                                    Toast.makeText(
                                        context,
                                        "Press again to quit",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    currentTime = System.currentTimeMillis()
                                } else {
                                    exitProcess(0)
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}