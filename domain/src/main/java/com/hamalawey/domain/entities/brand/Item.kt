package com.hamalawey.domain.entities.brand

data class Item(
    val calories: Any,
    val currency: String,
    val favorite: Any,
    val gtin: Any,
    val has_special_price: Boolean,
    val id: Int,
    val is_available: Boolean,
    val mpn: Any,
    val name: String,
    val price: Price,
    val promotion: Promotion,
    val regular_price: Price,
    val sale_price: Price,
    val sku: String,
    val starting_price: Any,
    val status: String,
    val thumbnail: String,
    val type: String,
    val url: String
)