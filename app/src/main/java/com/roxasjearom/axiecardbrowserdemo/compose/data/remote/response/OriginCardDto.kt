package com.roxasjearom.axiecardbrowserdemo.compose.data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OriginCardDto(
    val id: Int,
    val cardDamage: Int,
    val cardEnergy: String,
    val cardHeal: Int,
    val cardImage: String,
    val cardName: String,
    val cardShield: Int,
    val cardText: String?,
    val cardType: String,
    @Json(name = "class") val cardClass: String,
    val partId: String,
    val partName: String,
    val partType: String,
    val tags: String,
)
