package com.roxasjearom.axiecardbrowserdemo.compose.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.roxasjearom.axiecardbrowserdemo.compose.data.local.entity.OriginCardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OriginCardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCards(cardEntities: List<OriginCardEntity>)

    @Query("SELECT * FROM card_table")
    fun getCards(): Flow<List<OriginCardEntity>>
}
