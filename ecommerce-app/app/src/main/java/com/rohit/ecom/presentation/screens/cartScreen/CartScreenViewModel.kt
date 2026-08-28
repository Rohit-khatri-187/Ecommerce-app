package com.rohit.ecom.presentation.screens.cartScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rohit.ecom.data.remote.cart.dto.AddToCartDto
import com.rohit.ecom.data.remote.cart.dto.AddToCartResponseDto
import com.rohit.ecom.data.remote.cart.dto.GetCartItemsResponseDto
import com.rohit.ecom.domain.cart.usecases.CartUseCases
import com.rohit.ecom.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CartScreenViewModel @Inject constructor(
    private val cartUseCases: CartUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow<ApiResult<GetCartItemsResponseDto>>(ApiResult.Loading)
    val uiState = _uiState.asStateFlow()

    private val _addToCartState = MutableStateFlow<ApiResult<AddToCartResponseDto>?>(null)
    val addToCartState = _addToCartState.asStateFlow()

    init {
        getCartItems()
    }

    fun getCartItems() {
        viewModelScope.launch {
            cartUseCases.getAllCartItemUseCase().collect { result ->
                _uiState.value = result
            }
        }
    }

    fun addToCart(productId: Int, quantity: Int) {
        viewModelScope.launch {
            cartUseCases.addToCartUseCase(
                AddToCartDto(productId, quantity)
            ).collect { result ->
                _addToCartState.value = result
                // If adding was successful, refresh the cart items list
                if (result is ApiResult.Success) {
                    getCartItems()
                }
            }
        }
    }

    fun resetAddToCartState() {
        _addToCartState.value = null
    }
}
