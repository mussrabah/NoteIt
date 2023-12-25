package com.musscoding.noteit.presentation.main_screen

import com.musscoding.noteit.domain.model.Note

data class MainScreenState(
    val notes: List<Note> = emptyList()
)
