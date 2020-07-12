package com.example.note_mvp_sample.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class NoteEntity (
    val data: NoteContentsEntity
)

@Serializable
data class NoteContentsEntity (
    val contents: List<NoteContentEntity>
)

@Serializable
data class NoteContentEntity (
    val id: Int,
    val type: String,
    val status: String?,
    val name: String,
    val description: String? = null,
    val price: Int? = 0,
    val key: String? = null,
    val slug: String? = null,
    val publishAt: String? = null,
    val thumbnailExternalUrl: String? = null,
    val eyecatch: String? = null,
    val user: NoteUserEntity,
    val canRead: Boolean? = false,
    val isAuthor: Boolean? = false,
    val externalUrl: String? = null,
    val customDomain: NoteCustomDomain? = null,
    val body: String? = null,
    val isLimited: Boolean? = false,
    val isTrial: Boolean? = false,
    val canUpdate: Boolean? = false,
    val tweetText: String? = null,
    val additionalAttr: NoteAdditionalAttr? = null,
    val isRefund: Boolean? = false,
    val likeCount: Int? = 0
)

@Serializable
data class NoteUserEntity (
    val id: String? = null,
    val name: String? = null,
    val urlname: String? = null,
    val nickname: String? = null,
    val userProfileImagePath: String? = null,
    val customDomain: NoteCustomDomain? = null,
    val disableSupport: Boolean? = false,
    val likeAppealText: String? = null,
    val likeAppealImage: String? = null,
    val purchaseAppealTextNote: String? = null,
    val twitterNickname: String? = null
)

@Serializable
data class NoteCustomDomain (
    val id: Int? = 0,
    val tls: Boolean? = false,
    val host: String? = null,
    val type: String? = null,
    val key: String? = null
)

@Serializable
data class NoteAdditionalAttr (
    val anonymousLikeCount: Int? = 0,
    val formatted: Boolean? = false
)