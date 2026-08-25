package com.rohit.ecom.domain.usecases

import androidx.paging.PagingData
import com.rohit.ecom.data.remote.dto.Product
import com.rohit.ecom.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow

class GetProducts(
    private val productsRepository: ProductsRepository
) {

     operator fun invoke(): Flow<PagingData<Product>> {
        return productsRepository.getProducts();

    }

}