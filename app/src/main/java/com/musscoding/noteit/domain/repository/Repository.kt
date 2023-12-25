package com.musscoding.noteit.domain.repository

import com.musscoding.noteit.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun insertNote(note: Note)
    suspend fun deleteNote(note: Note)

    suspend fun updateNote(note: Note)

    fun getAllNotes(): Flow<List<Note>>
}