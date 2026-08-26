package com.rohit.ecom.data.remote.products.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rohit.ecom.data.remote.products.ProductsApi
import com.rohit.ecom.data.remote.products.ProductsPagingSource
import com.rohit.ecom.data.remote.products.dto.Product
import com.rohit.ecom.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow

class ProductsRepositoryImpl(
    private val productsApi: ProductsApi
) : ProductsRepository {
    override fun getProducts(): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ProductsPagingSource(productsApi)
            }
        ).flow
    }

    override suspend fun getProductById(id: Int): Product {
        return productsApi.getProductById(id)
    }
}