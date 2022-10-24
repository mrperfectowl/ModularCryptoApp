package com.example.data.mappers

import com.example.data.network.model.CoinResponse
import com.example.domain.model.Coin

fun List<CoinResponse>.toDomain(): List<Coin> {

    return map {
        Coin(
            id = it.id,
            isActive = it.isActive,
            name = it.name,
            rank = it.rank,
            symbol = it.symbol
        )
    }
}