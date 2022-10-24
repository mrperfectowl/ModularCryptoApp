package com.example.domain.usecase

import com.example.common.Resource
import com.example.domain.model.Coin
import com.example.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        emit(Resource.Loading())
        try {
            val response = repository.getCoins()
            emit(Resource.Success(data = response))

        } catch (e: Exception) {
            emit(Resource.Error("error occurred"))
        }
    }
}