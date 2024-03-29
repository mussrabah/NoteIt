package com.musscoding.noteit.note_crud.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.musscoding.noteit.note_crud.data.local.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase: RoomDatabase() {
    abstract val dao: NoteDao
}