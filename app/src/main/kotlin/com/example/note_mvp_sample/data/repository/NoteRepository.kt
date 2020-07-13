package com.example.note_mvp_sample.data.repository

import com.example.note_mvp_sample.data.entity.NoteDetailEntity
import com.example.note_mvp_sample.data.entity.NoteEntity

interface NoteRepository {
    suspend fun getNote(): NoteEntity?
    suspend fun getNoteDetail(id: String): NoteDetailEntity?
}