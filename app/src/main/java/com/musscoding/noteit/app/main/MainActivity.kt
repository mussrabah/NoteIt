package com.musscoding.noteit.app.main

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.Identity
import com.musscoding.noteit.note_crud.presentation.edit_note.EditNote
import com.musscoding.noteit.note_crud.presentation.main_screen.MainScreen
import com.musscoding.noteit.sign_in.presentation.signin.GoogleAuthUiClient
import com.musscoding.noteit.sign_in.presentation.signin.SignInScreen
import com.musscoding.noteit.sign_in.presentation.signin.SignInViewModel
import com.musscoding.noteit.ui.theme.NoteItTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import util.Route
import kotlin.system.exitProcess

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteItTheme {
                val navHostController = rememberNavController()
                NavHost(
                    navController = navHostController,
                    startDestination = Route.SIGN_IN
                ) {
                    composable(route = Route.SIGN_IN) {
                        val viewModel = hiltViewModel<SignInViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()

                        LaunchedEffect(key1 = Unit) {
                            if (googleAuthUiClient.getSignedInUser() != null) {
                                navHostController.navigate(Route.MAIN_NOTES_VIEW)
                            }
                        }

                        val launcher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.StartIntentSenderForResult(),
                            onResult = {result ->
                                if (result.resultCode == RESULT_OK) {
                                    lifecycleScope.launch {
                                        val signInResult = googleAuthUiClient.signInWithIntent(
                                            intent = result.data ?: return@launch
                                        )
                                        viewModel.onSignInResult(signInResult)
                                    }
                                }
                            }
                        )

                        LaunchedEffect(key1 = state.isSignInSuccessful) {
                            if (state.isSignInSuccessful) {
                                Toast.makeText(
                                    applicationContext,
                                    "Sign in successful",
                                    Toast.LENGTH_LONG
                                ).show()
                                navHostController.navigate(route = Route.MAIN_NOTES_VIEW)
                                viewModel.resetState()
                            }

                        }

                        SignInScreen(
                            signInState = state,
                            onSignInClick = {
                                Log.d("Acc", "Begin Sign In")
                                lifecycleScope.launch {
                                    val signInIntentSender = googleAuthUiClient.signIn()
                                    launcher.launch(
                                        IntentSenderRequest.Builder(
                                            signInIntentSender ?: return@launch
                                        ).build()
                                    )
                                }
                                Log.d("Acc", "End Sign In")
                            }
                        )
                    }

                    composable(route = Route.MAIN_NOTES_VIEW) {
                        MainScreen(
                            onNoteCreateClick = {
                                navHostController.navigate(Route.NOTE_EDIT)
                            },
                            userData = googleAuthUiClient.getSignedInUser()
                        )
                    }

                    composable(route = Route.NOTE_EDIT) {
                        EditNote(
                            onBackClicked = {
                                navHostController.navigateUp()
                            }
                        )
                    }

                }
                backPressedTwoTimes(navHostController)
            }
        }
    }

    private fun backPressedTwoTimes(
        navHostController: NavHostController
    ) {
        var currentTime1 = 0L
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (navHostController.currentDestination?.route != Route.SIGN_IN) {
                        navHostController.navigateUp()
                    } else {
                        val elapsedTime = System.currentTimeMillis() - currentTime1
                        if (elapsedTime > 3_000) {
                            Toast.makeText(
                                applicationContext,
                                "Press again to quit",
                                Toast.LENGTH_SHORT
                            ).show()
                            currentTime1 = System.currentTimeMillis()
                        } else {
                            exitProcess(0)
                        }
                    }
                }
            }
        )
    }
}