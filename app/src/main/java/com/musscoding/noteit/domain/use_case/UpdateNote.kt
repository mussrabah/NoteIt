package com.musscoding.noteit.domain.use_case

import com.musscoding.noteit.domain.model.Note
import com.musscoding.noteit.domain.repository.Repository

class UpdateNote(
    private val repository: Repository
) {
    suspend operator fun invoke(note: Note) {
        repository.updateNote(note)
    }
}