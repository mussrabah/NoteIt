package com.musscoding.noteit.domain.use_case

data class UseCases(
    val addNote: AddNote,
    val deleteNote: DeleteNote,
    val updateNote: UpdateNote,
    val getAllNotes: GetAllNotes
)
