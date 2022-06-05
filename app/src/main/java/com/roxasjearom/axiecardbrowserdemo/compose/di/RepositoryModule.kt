package com.roxasjearom.axiecardbrowserdemo.compose.di

import com.roxasjearom.axiecardbrowserdemo.compose.data.repository.CardRepositoryImpl
import com.roxasjearom.axiecardbrowserdemo.compose.domain.repository.CardRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @ViewModelScoped
    @Binds
    abstract fun bindCardRepository(impl: CardRepositoryImpl): CardRepository
}
