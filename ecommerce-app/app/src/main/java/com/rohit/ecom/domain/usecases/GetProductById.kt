package com.rohit.ecom.domain.usecases

import com.rohit.ecom.data.remote.dto.Product
import com.rohit.ecom.domain.repository.ProductsRepository
import com.rohit.ecom.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetProductById(
    private val productsRepository: ProductsRepository
) {

     operator fun invoke(id: Int): Flow<ApiResult<Product>> = flow {

         emit(ApiResult.Loading)

         try {
             val product = productsRepository.getProductById(id)
             emit(ApiResult.Success(product))
         }catch (e: Exception){
             emit(
                 ApiResult.Error(
                     message = e.message ?: "Something went wrong",
                     cause = e
                 )
             )
         }


    }

}