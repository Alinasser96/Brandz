package com.hamalawey.domain.entities.item

data class Image(
    val alt: String,
    val id: Int,
    val main: Boolean,
    val sort: Int,
    val three_d_image_url: String,
    val type: String,
    val url: String,
    val video_url: String
)