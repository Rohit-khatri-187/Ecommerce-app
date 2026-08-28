package com.rohit.ecom.data.remote.cart

import com.rohit.ecom.data.remote.cart.dto.AddToCartDto
import com.rohit.ecom.data.remote.cart.dto.AddToCartResponseDto
import com.rohit.ecom.data.remote.cart.dto.GetCartItemsResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CartApi {

    @POST("carts")
    suspend fun addToCart(
        @Body addToCartRequestDto : AddToCartDto
    ): AddToCartResponseDto

    @GET("carts")
    suspend fun getAllCartItems() : GetCartItemsResponseDto

}