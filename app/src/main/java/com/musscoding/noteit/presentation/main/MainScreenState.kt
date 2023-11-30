package com.musscoding.noteit.presentation.main

import com.musscoding.noteit.domain.model.Note

data class MainScreenState(
    val notes: List<Note> = emptyList()
)
