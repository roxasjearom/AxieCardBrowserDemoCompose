package com.roxasjearom.axiecardbrowserdemo.compose.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
data class OriginCardEntity(
    @PrimaryKey @ColumnInfo(name = "card_id") val id: Int,
    @ColumnInfo(name = "card_damage") val cardDamage: Int,
    @ColumnInfo(name = "card_energy") val cardEnergy: String,
    @ColumnInfo(name = "card_heal") val cardHeal: Int,
    @ColumnInfo(name = "card_image") val cardImage: String,
    @ColumnInfo(name = "card_name") val cardName: String,
    @ColumnInfo(name = "card_shield") val cardShield: Int,
    @ColumnInfo(name = "card_text") val cardText: String?,
    @ColumnInfo(name = "card_type") val cardType: String,
    @ColumnInfo(name = "card_class") val cardClass: String,
    @ColumnInfo(name = "part_id") val partId: String,
    @ColumnInfo(name = "part_name") val partName: String,
    @ColumnInfo(name = "part_type") val partType: String,
    @ColumnInfo(name = "tags") val tags: String,
)
