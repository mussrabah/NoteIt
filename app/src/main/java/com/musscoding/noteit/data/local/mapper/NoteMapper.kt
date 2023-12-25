package com.musscoding.noteit.data.local.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.musscoding.noteit.data.local.entity.NoteEntity
import com.musscoding.noteit.domain.model.Note
import java.time.LocalDateTime
import java.time.Month

@RequiresApi(Build.VERSION_CODES.O)
fun NoteEntity.toNote(): Note {
    return Note(
        id = id,
        title = title,
        content = content,
        label = label,
        timeStamp = LocalDateTime.of(
            year,
            Month.of(month),
            day,
            hour,
            minute,
            seconds
        )
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun Note.toNoteEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        content = content,
        label = label,
        day = timeStamp.dayOfMonth,
        month = timeStamp.monthValue,
        year = timeStamp.year,
        hour = timeStamp.hour,
        minute = timeStamp.minute,
        seconds = timeStamp.second
    )
}