package com.example.modularcryptoapp.ui.main

import com.example.domain.model.Coin

sealed interface CoinListState {
    object Loading : CoinListState
    class Success(val coins: List<Coin>) : CoinListState
    class Error(val error: String) : CoinListState
}