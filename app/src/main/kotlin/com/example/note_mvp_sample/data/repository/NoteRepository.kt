package com.example.note_mvp_sample.data.repository

import com.example.note_mvp_sample.data.entity.NoteEntity

interface NoteRepository {
    suspend fun get(): NoteEntity?
}