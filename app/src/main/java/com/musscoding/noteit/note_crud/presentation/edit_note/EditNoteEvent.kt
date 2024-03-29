package com.musscoding.noteit.note_crud.presentation.edit_note

sealed class EditNoteEvent {
    data class OnSaveNote(val title: String, val content: String): EditNoteEvent()
}