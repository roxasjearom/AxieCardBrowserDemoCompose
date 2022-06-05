package com.roxasjearom.axiecardbrowserdemo.compose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.roxasjearom.axiecardbrowserdemo.compose.data.local.dao.OriginCardDao
import com.roxasjearom.axiecardbrowserdemo.compose.data.local.entity.OriginCardEntity

@Database(
    entities = [OriginCardEntity::class],
    version = 1,
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun originCardDao(): OriginCardDao
}
