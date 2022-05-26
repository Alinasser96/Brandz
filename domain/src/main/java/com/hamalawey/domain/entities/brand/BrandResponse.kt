package com.hamalawey.domain.entities.brand

data class BrandResponse(
    val brand: Brand,
    val cursor: Cursor,
    val `data`: List<Item>,
    val status: Int,
    val success: Boolean
)