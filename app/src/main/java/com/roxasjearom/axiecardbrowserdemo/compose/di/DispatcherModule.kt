package com.roxasjearom.axiecardbrowserdemo.compose.di

import com.roxasjearom.axiecardbrowserdemo.compose.utils.DefaultCoroutineDispatcher
import com.roxasjearom.axiecardbrowserdemo.compose.utils.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatcherModule {

    @Singleton
    @Binds
    abstract fun provideDispatcherProvider(impl: DefaultCoroutineDispatcher): DispatcherProvider
}
