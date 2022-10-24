package com.example.data.network.di

import com.example.common.Constant
import com.example.data.network.ApiService
import com.example.data.repository.CoinRepositoryImpl
import com.example.domain.model.Coin
import com.example.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
     fun provideRetrofit(): Retrofit {
         return Retrofit.Builder().baseUrl(Constant.BASE_URL)
             .addConverterFactory(GsonConverterFactory.create())
             .build()
     }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideGetCoinsRepository(apiService: ApiService): CoinRepository {
        return CoinRepositoryImpl(apiService = apiService)
    }
}