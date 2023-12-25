package com.musscoding.noteit.data.local.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.musscoding.noteit.data.local.NoteDao
import com.musscoding.noteit.data.local.mapper.toNote
import com.musscoding.noteit.data.local.mapper.toNoteEntity
import com.musscoding.noteit.domain.model.Note
import com.musscoding.noteit.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


@RequiresApi(Build.VERSION_CODES.O)
class RepositoryImpl(
    private val dao: NoteDao,

): Repository{
    override suspend fun insertNote(note: Note) {
        dao.insertNote(note.toNoteEntity())
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note.toNoteEntity())
    }

    override suspend fun updateNote(note: Note) {
        dao.updateNote(note.toNoteEntity())
    }

    override fun getAllNotes(): Flow<List<Note>> {
        return dao
            .getAllNotes()
            .map {entities ->
                entities.map {
                    it.toNote()
                }
            }
    }
}