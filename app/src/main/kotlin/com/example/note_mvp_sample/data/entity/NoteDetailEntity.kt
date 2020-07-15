package com.example.note_mvp_sample.data.entity

import kotlinx.serialization.Serializable

/**
 * NOTE:
 * プロパティが多すぎて辛かったので、アプリとして必要なもののみ宣言し、
 * それ以外は無視するようにしています。
 */

@Serializable
data class NoteDetailEntity (
    val data: NoteDetailContentEntity
)

@Serializable
data class NoteDetailContentEntity (
    val like_count: Int? = 0,
    val user: NoteDetailUserEntity? = null,
    val name: String? = null,
    val body: String? = null,
    val eyecatch: String? = null,
    val publish_at: String? = null
)

@Serializable
data class NoteDetailUserEntity (
    val id: String? = null,
    val urlname: String? = null,
    val nickname: String? = null,
    val user_profile_image_path: String? = null
)