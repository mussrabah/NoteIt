package com.musscoding.noteit.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.musscoding.noteit.data.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteEntity: NoteEntity)

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)

    @Query(
        """
            SELECT * FROM note
        """
    )
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Update
    suspend fun updateNote(noteEntity: NoteEntity)
}