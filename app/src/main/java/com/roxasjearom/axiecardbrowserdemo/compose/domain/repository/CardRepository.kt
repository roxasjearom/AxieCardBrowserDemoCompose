package com.roxasjearom.axiecardbrowserdemo.compose.domain.repository

import com.roxasjearom.axiecardbrowserdemo.compose.domain.model.OriginCard
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    suspend fun fetchData()
    suspend fun getCards(): Flow<List<OriginCard>>
}
