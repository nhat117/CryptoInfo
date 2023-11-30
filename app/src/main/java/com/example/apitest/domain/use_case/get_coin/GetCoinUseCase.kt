package com.example.apitest.domain.use_case.get_coin

import com.example.apitest.common.Resource
import com.example.apitest.domain.model.CoinDetail
import com.example.apitest.domain.repository.CoinRepository
import com.example.apitest.data.remote.dto.toCoinDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(private val repository: CoinRepository) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId)
            emit(Resource.Success(coin.toCoinDetail()))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpecterd errod occured"))

        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach the server set your internet connection"))
        }
    }
}