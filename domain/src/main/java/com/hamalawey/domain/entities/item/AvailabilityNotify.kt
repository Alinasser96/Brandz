package com.hamalawey.domain.entities.item

data class AvailabilityNotify(
    val email: Boolean,
    val mobile: Boolean,
    val sms: Boolean,
    val whatsapp: Boolean
)