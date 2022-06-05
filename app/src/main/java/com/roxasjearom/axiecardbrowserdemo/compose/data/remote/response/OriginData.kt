package com.roxasjearom.axiecardbrowserdemo.compose.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OriginData(
    val cards: List<OriginCardDto>
)
