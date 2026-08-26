package com.rohit.ecom.presentation.screens.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rohit.ecom.data.remote.products.dto.Product
import com.rohit.ecom.domain.usecases.ProductsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productsUseCases: ProductsUseCases
) : ViewModel() {

    val products: Flow<PagingData<Product>> =
        productsUseCases
            .getProducts()
            .cachedIn(viewModelScope)
}