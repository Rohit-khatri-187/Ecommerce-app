package com.rohit.ecom.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.rohit.ecom.data.remote.auth.AuthApi
import com.rohit.ecom.data.remote.auth.repository.AuthRepositoryImpl
import com.rohit.ecom.data.remote.products.ProductsApi
import com.rohit.ecom.data.remote.products.repository.ProductsRepositoryImpl
import com.rohit.ecom.domain.auth.repository.AuthRepository
import com.rohit.ecom.domain.auth.usecases.AuthUseCases
import com.rohit.ecom.domain.auth.usecases.LoginUseCase
import com.rohit.ecom.domain.auth.usecases.RegisterUseCase
import com.rohit.ecom.domain.manager.tokenmanager.TokenPrefImpl
import com.rohit.ecom.domain.manager.tokenmanager.repository.TokenPref
import com.rohit.ecom.domain.products.repository.ProductsRepository
import com.rohit.ecom.domain.products.usecases.GetProductById
import com.rohit.ecom.domain.products.usecases.GetProducts
import com.rohit.ecom.domain.products.usecases.ProductsUseCases
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
    fun provideAuthApi(retrofit: Retrofit): AuthApi{
        return retrofit.create(AuthApi::class.java);
    }



    @Provides
    @Singleton
    fun provideTokenPref(dataStore: DataStore<Preferences>): TokenPref {
        return TokenPrefImpl(dataStore)
    }


    @Provides
    @Singleton
    fun provideProductRepository(productsApi: ProductsApi): ProductsRepository {
        return ProductsRepositoryImpl(productsApi)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(authApi: AuthApi): AuthRepository {
        return AuthRepositoryImpl(authApi)
    }

    @Provides
    @Singleton
    fun provideProductCases(
        productsRepository: ProductsRepository
    ): ProductsUseCases {
        return ProductsUseCases(
            getProducts = GetProducts(productsRepository),
            getProductById = GetProductById(productsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideAuthCases(
        authRepository: AuthRepository,
        tokenPref: TokenPref
    ): AuthUseCases {
        return AuthUseCases(
            login = LoginUseCase(authRepository, tokenPref),
            register = RegisterUseCase(authRepository)
        )
    }




}