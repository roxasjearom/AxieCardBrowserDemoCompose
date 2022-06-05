package com.roxasjearom.axiecardbrowserdemo.compose.di

import android.content.Context
import androidx.room.Room
import com.roxasjearom.axiecardbrowserdemo.compose.data.local.AppDatabase
import com.roxasjearom.axiecardbrowserdemo.compose.data.local.CardLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "origin.db").build()
    }

    @Provides
    fun provideCardLocalDataSource(appDatabase: AppDatabase): CardLocalDataSource {
        return CardLocalDataSource(appDatabase = appDatabase)
    }
}
