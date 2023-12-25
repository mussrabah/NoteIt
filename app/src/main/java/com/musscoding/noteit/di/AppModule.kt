package com.musscoding.noteit.di

import android.app.Application
import android.os.Build
import android.provider.DocumentsContract.Root
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.musscoding.noteit.data.local.NoteDatabase
import com.musscoding.noteit.data.local.repository.RepositoryImpl
import com.musscoding.noteit.domain.repository.Repository
import com.musscoding.noteit.domain.use_case.AddNote
import com.musscoding.noteit.domain.use_case.DeleteNote
import com.musscoding.noteit.domain.use_case.GetAllNotes
import com.musscoding.noteit.domain.use_case.UpdateNote
import com.musscoding.noteit.domain.use_case.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase{
        return Room
            .databaseBuilder(
                app,
                NoteDatabase::class.java,
                "note_db"
            )
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    @Singleton
    fun provideRepository(db: NoteDatabase): Repository {
        return RepositoryImpl(
            dao = db.dao
        )
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            addNote = AddNote(repository),
            deleteNote = DeleteNote(repository),
            updateNote = UpdateNote(repository),
            getAllNotes = GetAllNotes(repository)
        )
    }
}