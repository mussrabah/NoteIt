package com.musscoding.noteit.note_crud.presentation.edit_note

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.musscoding.noteit.note_crud.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import util.UiEvent
import javax.inject.Inject
import com.musscoding.noteit.note_crud.domain.model.Note
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {
    var state by mutableStateOf(EditNoteState())
        private set

    private val _uiEvent = Channel<UiEvent>()

    val uiEvent = _uiEvent.receiveAsFlow()


    fun onEvent(event: EditNoteEvent) {
        when(event) {
            is EditNoteEvent.OnSaveNote -> {
                state = state.copy(
                    currentNote = Note(
                        title = event.title,
                        content = event.content,
                        label = "label",
                        timeStamp = LocalDateTime.now()
                    )
                )
                viewModelScope.launch {
                    useCases.addNote(state.currentNote!!)
                    _uiEvent.send(UiEvent.Success)
                }
            }
        }
    }
}