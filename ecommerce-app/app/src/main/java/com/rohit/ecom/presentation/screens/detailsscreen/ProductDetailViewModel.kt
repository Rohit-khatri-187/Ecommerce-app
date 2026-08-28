package com.rohit.ecom.presentation.screens.detailsscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rohit.ecom.data.remote.cart.dto.AddToCartDto
import com.rohit.ecom.data.remote.cart.dto.AddToCartResponseDto
import com.rohit.ecom.data.remote.products.dto.Product
import com.rohit.ecom.domain.cart.usecases.CartUseCases
import com.rohit.ecom.domain.products.usecases.ProductsUseCases
import com.rohit.ecom.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productsUseCases: ProductsUseCases,
    private val cartUseCases: CartUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow<ApiResult<Product>>(ApiResult.Loading)
    val uiState = _uiState.asStateFlow()

    private val _addToCartState = MutableStateFlow<ApiResult<AddToCartResponseDto>?>(null)
    val addToCartState = _addToCartState.asStateFlow()

    fun getProduct(id: Int) {
        viewModelScope.launch {
            productsUseCases.getProductById(id).collect { result ->
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
            }
        }
    }

    fun resetAddToCartState() {
        _addToCartState.value = null
    }
}
