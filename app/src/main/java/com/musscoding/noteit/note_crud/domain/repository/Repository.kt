package com.musscoding.noteit.note_crud.domain.repository

import com.musscoding.noteit.note_crud.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun insertNote(note: Note)
    suspend fun deleteNote(note: Note)

    suspend fun updateNote(note: Note)

    fun getAllNotes(): Flow<List<Note>>
}