package com.example.apitest.presentation.coinlist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitest.common.Resource
import com.example.apitest.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {
    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { res ->
            when (res) {
                is Resource.Success -> {
                    _state.value = CoinListState(coins = res.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value =
                        CoinListState(error = res.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }

            }
        }.launchIn(viewModelScope)
    }
}