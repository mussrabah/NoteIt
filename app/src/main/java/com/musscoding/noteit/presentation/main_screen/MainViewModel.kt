package com.musscoding.noteit.presentation.main_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.musscoding.noteit.domain.model.Note
import com.musscoding.noteit.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import util.UiEvent
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    var state by mutableStateOf(MainScreenState())
        private set
    init {
        getNotes()
        Log.d("MVM", "notes are: ${state.notes}")
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