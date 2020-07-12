package com.example.note_mvp_sample.domain.service

import com.example.note_mvp_sample.data.entity.NoteContentEntity
import com.example.note_mvp_sample.data.repository.NoteRepository
import com.example.note_mvp_sample.data.source.NoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class NoteService {
    private val noteRepository: NoteRepository = NoteDataSource()

    fun getNotesAsync(): Deferred<List<NoteContentEntity>?> = CoroutineScope(Dispatchers.IO).async {
        return@async noteRepository.get()?.data?.contents
    }
}