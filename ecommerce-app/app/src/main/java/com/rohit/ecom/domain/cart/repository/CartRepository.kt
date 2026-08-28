package com.rohit.ecom.domain.cart.repository

import com.rohit.ecom.data.remote.cart.dto.AddToCartDto
import com.rohit.ecom.data.remote.cart.dto.AddToCartResponseDto
import com.rohit.ecom.data.remote.cart.dto.GetCartItemsResponseDto

interface CartRepository {

    suspend fun addToCart( addToCartDto: AddToCartDto ) : AddToCartResponseDto

    suspend fun getAllProducts() : GetCartItemsResponseDto

}