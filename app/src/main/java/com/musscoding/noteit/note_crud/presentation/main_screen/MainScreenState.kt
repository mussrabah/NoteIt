package com.musscoding.noteit.note_crud.presentation.main_screen

import com.musscoding.noteit.note_crud.domain.model.Note
import com.musscoding.noteit.sign_in.presentation.signin.UserData

data class MainScreenState(
    val notes: List<Note> = emptyList(),
    val signedInUser: UserData? = null
)
