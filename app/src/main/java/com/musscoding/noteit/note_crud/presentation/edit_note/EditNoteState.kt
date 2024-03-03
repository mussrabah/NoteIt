package com.musscoding.noteit.note_crud.presentation.edit_note

import com.musscoding.noteit.note_crud.domain.model.Note

data class EditNoteState(
    val currentNote: Note? = null
)