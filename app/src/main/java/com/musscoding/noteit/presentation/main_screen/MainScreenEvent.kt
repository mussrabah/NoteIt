package com.musscoding.noteit.presentation.main_screen

import com.musscoding.noteit.domain.model.Note

sealed class MainScreenEvent {
    data class OnDeleteNote(val note: Note): MainScreenEvent()
}