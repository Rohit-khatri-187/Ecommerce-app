package com.rohit.ecom.domain.cart.usecases

import com.rohit.ecom.data.remote.cart.dto.AddToCartDto
import com.rohit.ecom.data.remote.cart.dto.GetCartItemsResponseDto
import com.rohit.ecom.domain.cart.repository.CartRepository
import com.rohit.ecom.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllCartItemUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    operator fun invoke(): Flow<ApiResult<GetCartItemsResponseDto>> = flow {
        emit(ApiResult.Loading)

        try {

            val response = cartRepository.getAllProducts();

            emit(ApiResult.Success(response))

        }catch (e : Exception){
            emit(
                ApiResult.Error(
                    message = e.message ?: "Something went wrong",
                    cause = e
                )
            )
        }

    }

}