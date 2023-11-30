package com.example.apitest.domain.repository

import com.example.apitest.data.remote.dto.CoinDTO
import com.example.apitest.data.remote.dto.CoinDetailDTO

interface CoinRepository {
    suspend fun getCoins():List<CoinDTO>

    suspend fun getCoinById(coinId: String): CoinDetailDTO
}