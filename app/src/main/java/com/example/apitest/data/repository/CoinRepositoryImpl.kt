package com.example.apitest.data.repository

import com.example.apitest.domain.repository.CoinRepository
import com.example.apitest.data.remote.CoinPaprikaAPI
import com.example.apitest.data.remote.dto.CoinDTO
import com.example.apitest.data.remote.dto.CoinDetailDTO
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaAPI
): CoinRepository {
    override suspend fun getCoins(): List<CoinDTO> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDTO {
        return api.getCoinById(coinId)
    }


}