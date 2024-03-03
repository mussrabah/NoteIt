package com.musscoding.noteit.note_crud.presentation.main_screen

import com.musscoding.noteit.note_crud.domain.model.Note

sealed class MainScreenEvent {
    data class OnDeleteNote(val note: Note): MainScreenEvent()
}