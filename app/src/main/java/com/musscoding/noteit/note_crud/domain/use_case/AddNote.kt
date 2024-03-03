package com.musscoding.noteit.note_crud.domain.use_case

import com.musscoding.noteit.note_crud.domain.model.Note
import com.musscoding.noteit.note_crud.domain.repository.Repository

class AddNote(
    private val repository: Repository
) {
    suspend operator fun invoke(note: Note) {
        repository.insertNote(note)
    }
}