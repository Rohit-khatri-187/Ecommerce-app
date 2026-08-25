package com.rohit.ecom.di

import com.rohit.ecom.data.remote.ProductsApi
import com.rohit.ecom.data.remote.repository.ProductsRepositoryImpl
import com.rohit.ecom.domain.repository.ProductsRepository
import com.rohit.ecom.domain.usecases.GetProductById
import com.rohit.ecom.domain.usecases.GetProducts
import com.rohit.ecom.domain.usecases.ProductsUseCases
import com.rohit.ecom.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideProductApi(retrofit: Retrofit): ProductsApi{
        return retrofit.create(ProductsApi::class.java);
    }

    @Provides
    @Singleton
    fun provideProductRepository(productsApi: ProductsApi): ProductsRepository {
        return ProductsRepositoryImpl(productsApi)
    }

    @Provides
    @Singleton
    fun provideNewsCases(
        productsRepository: ProductsRepository
    ): ProductsUseCases {
        return ProductsUseCases(
            getProducts = GetProducts(productsRepository),
            getProductById = GetProductById(productsRepository)
        )
    }

}