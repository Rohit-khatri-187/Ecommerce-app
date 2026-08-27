package com.rohit.ecom.domain.products.repository

import androidx.paging.PagingData
import com.rohit.ecom.data.remote.products.dto.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

     fun getProducts(): Flow<PagingData<Product>>

     suspend fun getProductById(id: Int) : Product

}