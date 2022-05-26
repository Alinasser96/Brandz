package com.hamalawey.domain.entities.item

import com.google.gson.annotations.SerializedName

data class Features(
    val availability_notify: AvailabilityNotify,
    @SerializedName("quick_buy") val quickBuy: Boolean,
    val show_rating: Boolean,
    val show_remaining_quantity: Boolean,
    val show_you_may_like: Boolean
)