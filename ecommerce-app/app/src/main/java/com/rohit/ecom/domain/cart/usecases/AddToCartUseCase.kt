package com.rohit.ecom.domain.cart.usecases

import com.rohit.ecom.data.remote.cart.dto.AddToCartDto
import com.rohit.ecom.data.remote.cart.dto.AddToCartResponseDto
import com.rohit.ecom.data.remote.cart.dto.GetCartItemsResponseDto
import com.rohit.ecom.domain.cart.repository.CartRepository
import com.rohit.ecom.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    operator fun invoke(addToCartDto: AddToCartDto): Flow<ApiResult<AddToCartResponseDto>> = flow {
        emit(ApiResult.Loading)

        try {

            val response = cartRepository.addToCart(addToCartDto);

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