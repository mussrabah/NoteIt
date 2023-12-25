package com.musscoding.noteit.domain.use_case

import com.musscoding.noteit.domain.model.Note
import com.musscoding.noteit.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetAllNotes(
    private val repository: Repository
) {
    operator fun invoke(): Flow<List<Note>> {
        return repository.getAllNotes()
    }
}