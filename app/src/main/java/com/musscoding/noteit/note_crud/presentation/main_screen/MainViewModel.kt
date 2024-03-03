package com.musscoding.noteit.note_crud.presentation.main_screen

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.Identity
import com.musscoding.noteit.note_crud.domain.use_case.UseCases
import com.musscoding.noteit.sign_in.presentation.signin.GoogleAuthUiClient
import com.musscoding.noteit.sign_in.presentation.signin.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import util.UiEvent
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: UseCases,
    application: Application
   // private val googleAuthUiClient: GoogleAuthUiClient
): ViewModel() {


    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = application,
            oneTapClient = Identity.getSignInClient(application)
        )
    }
    var state by mutableStateOf(MainScreenState())
        private set
    init {
        getNotes()
        Log.d("MVM", "notes are: ${state.notes}")
    }

    fun setUserData(userData: UserData?) {
        state = state.copy(
            signedInUser = userData
        )
    }

    private fun getNotes() {
            useCases
                .getAllNotes()
                .onEach {
                    state = state.copy(
                        notes = it
                    )
                }
                .launchIn(viewModelScope)
    }

    private val _uiEvent = Channel<UiEvent>()

    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: MainScreenEvent) {
        when(event) {
            is MainScreenEvent.OnDeleteNote -> {
                viewModelScope.launch {
                    useCases.deleteNote(event.note)
                }
            }
            else -> {}
        }
    }
}