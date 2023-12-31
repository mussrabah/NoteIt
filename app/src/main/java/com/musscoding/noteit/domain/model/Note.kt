package com.musscoding.noteit.domain.model

import java.time.LocalDateTime

data class Note(
    val id: Int? = null,
    val title: String,
    val content: String,
    val label: String,
    val timeStamp: LocalDateTime
)
