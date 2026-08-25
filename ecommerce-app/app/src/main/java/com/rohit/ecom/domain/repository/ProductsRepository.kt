package com.rohit.ecom.domain.repository

import androidx.paging.PagingData
import com.rohit.ecom.data.remote.dto.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

     fun getProducts(): Flow<PagingData<Product>>

     suspend fun getProductById(id: Int) : Product

}