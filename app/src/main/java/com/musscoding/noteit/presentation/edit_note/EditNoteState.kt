package com.musscoding.noteit.presentation.edit_note

import com.musscoding.noteit.domain.model.Note

data class EditNoteState(
    val currentNote: Note? = null
)