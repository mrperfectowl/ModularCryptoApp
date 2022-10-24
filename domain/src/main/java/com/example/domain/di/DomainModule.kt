package com.example.domain.di

import com.example.domain.repository.CoinRepository
import com.example.domain.usecase.GetCoinsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideGetCoinsUseCase(repository: CoinRepository): GetCoinsUseCase {
        return GetCoinsUseCase(repository)
    }
}