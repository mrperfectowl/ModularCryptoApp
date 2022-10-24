package com.example.data.repository

import com.example.data.mappers.toDomain
import com.example.data.network.ApiService
import com.example.data.network.utils.SafeApiRequest
import com.example.domain.model.Coin
import com.example.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): CoinRepository, SafeApiRequest() {

    override suspend fun getCoins(): List<Coin> {
        val response = safeApiRequest {
            apiService.getCoins()
        }
        return response.toDomain()?: emptyList()
    }
}