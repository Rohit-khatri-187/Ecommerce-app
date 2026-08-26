package com.rohit.ecom.presentation.screens.detailsscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rohit.ecom.data.remote.products.dto.Product
import com.rohit.ecom.domain.usecases.ProductsUseCases
import com.rohit.ecom.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productsUseCases: ProductsUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow<ApiResult<Product>>(ApiResult.Loading)

    val uiState = _uiState.asStateFlow()

    fun getProduct(id: Int){
        viewModelScope.launch {
            productsUseCases.getProductById(id).collect { result ->
                _uiState.value = result
            }

        }
    }
}
