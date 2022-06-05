package com.roxasjearom.axiecardbrowserdemo.compose.data.repository

import com.roxasjearom.axiecardbrowserdemo.compose.data.local.CardLocalDataSource
import com.roxasjearom.axiecardbrowserdemo.compose.data.mapper.toEntity
import com.roxasjearom.axiecardbrowserdemo.compose.data.mapper.toOriginCard
import com.roxasjearom.axiecardbrowserdemo.compose.data.remote.CardRemoteDataSource
import com.roxasjearom.axiecardbrowserdemo.compose.domain.model.OriginCard
import com.roxasjearom.axiecardbrowserdemo.compose.domain.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val remoteDataSource: CardRemoteDataSource,
    private val localDataSource: CardLocalDataSource,
) : CardRepository {

    override suspend fun fetchData() {
        val cards = remoteDataSource.getOriginData().cards.map { it.toEntity() }
        localDataSource.saveCards(cards)
    }

    override suspend fun getCards(): Flow<List<OriginCard>> {
        return localDataSource.getCards().distinctUntilChanged().map { cards ->
            cards.map { it.toOriginCard() }
        }
    }
}
