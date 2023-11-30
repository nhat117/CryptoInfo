package com.example.apitest.domain.use_case.get_coins

import com.example.apitest.common.Resource
import com.example.apitest.domain.model.Coin
import com.example.apitest.domain.repository.CoinRepository
import com.example.apitest.data.remote.dto.toCoin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val repository: CoinRepository) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins()
            emit(Resource.Success(coins.map { it.toCoin() }))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpecterd errod occured"))

        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach the server set your internet connection"))
        }
    }
}