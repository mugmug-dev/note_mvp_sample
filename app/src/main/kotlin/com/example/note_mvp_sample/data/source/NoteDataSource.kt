package com.example.note_mvp_sample.data.source

import com.example.note_mvp_sample.app.Constants
import com.example.note_mvp_sample.data.entity.NoteDetailEntity
import com.example.note_mvp_sample.data.entity.NoteEntity
import com.example.note_mvp_sample.data.repository.NoteRepository
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class NoteDataSource : NoteRepository {
    companion object {
        private val jsonConfig = JsonConfiguration.Stable.copy(
            ignoreUnknownKeys = true,
            isLenient = true,
            serializeSpecialFloatingPointValues = true,
            useArrayPolymorphism = true
        )
    }

    override suspend fun getNote(): NoteEntity? = suspendCoroutine { continuation ->
        val (_, _, result) = Constants.NOTE_API_ENDPOINT.httpGet().responseString()
        when (result) {
            is Result.Failure -> {
                continuation.resume(null)
            }
            is Result.Success -> {
                val response = result.get()
                val entity = Json(jsonConfig).parse(NoteEntity.serializer(), response)
                continuation.resume(entity)
            }
        }
    }

    override suspend fun getNoteDetail(id: String): NoteDetailEntity? = suspendCoroutine { continuation ->
        val (_, _, result) = "${Constants.NOTE_DETAIL_BASE_ENDPOINT}/$id".httpGet().responseString()
        when (result) {
            is Result.Failure -> {
                continuation.resume(null)
            }
            is Result.Success -> {
                val response = result.get()
                val entity = Json(jsonConfig).parse(NoteDetailEntity.serializer(), response)
                continuation.resume(entity)
            }
        }
    }
}