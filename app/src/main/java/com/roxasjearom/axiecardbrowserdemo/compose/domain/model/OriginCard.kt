package com.roxasjearom.axiecardbrowserdemo.compose.domain.model

data class OriginCard(
    val cardDamage: Int,
    val cardEnergy: String,
    val cardHeal: Int,
    val cardImage: String,
    val cardName: String,
    val cardShield: Int,
    val cardText: String?,
    val cardType: String,
    val cardClass: String,
    val tags: String,
    val partType: String,
)
