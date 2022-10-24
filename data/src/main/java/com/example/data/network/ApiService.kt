package com.example.data.network

import com.example.data.network.model.CoinResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/v1/coins")
    suspend fun getCoins(): Response<List<CoinResponse>>
}