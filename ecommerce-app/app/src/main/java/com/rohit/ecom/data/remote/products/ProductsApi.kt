package com.rohit.ecom.data.remote.products

import com.rohit.ecom.data.remote.products.dto.Product
import com.rohit.ecom.data.remote.products.dto.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsApi {

    @GET("products")
    suspend fun getProducts(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): ProductsResponse

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ): Product

}