package com.musscoding.noteit.note_crud.domain.use_case

import com.musscoding.noteit.note_crud.domain.model.Note
import com.musscoding.noteit.note_crud.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetAllNotes(
    private val repository: Repository
) {
    operator fun invoke(): Flow<List<Note>> {
        return repository.getAllNotes()
    }
}