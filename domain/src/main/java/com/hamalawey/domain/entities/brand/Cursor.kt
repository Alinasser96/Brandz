package com.hamalawey.domain.entities.brand

data class Cursor(
    val count: Int,
    val current: Int,
    val next: String?,
    val previous: String
)