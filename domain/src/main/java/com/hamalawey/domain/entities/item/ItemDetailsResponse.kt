package com.hamalawey.domain.entities.item

data class ItemDetailsResponse(
    val `data`: ItemDetails,
    val status: Int,
    val success: Boolean
)