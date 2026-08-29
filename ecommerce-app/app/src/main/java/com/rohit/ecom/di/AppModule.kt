package com.rohit.ecom.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.rohit.ecom.data.remote.auth.AuthApi
import com.rohit.ecom.data.remote.auth.repository.AuthRepositoryImpl
import com.rohit.ecom.data.remote.cart.CartApi
import com.rohit.ecom.data.remote.cart.repository.CartRepositoryImpl
import com.rohit.ecom.data.remote.interceptors.AuthInterceptor
import com.rohit.ecom.data.remote.products.ProductsApi
import com.rohit.ecom.data.remote.products.repository.ProductsRepositoryImpl
import com.rohit.ecom.data.remote.profile.ProfileApi
import com.rohit.ecom.data.remote.profile.repository.ProfileRepositoryImpl
import com.rohit.ecom.domain.auth.repository.AuthRepository
import com.rohit.ecom.domain.auth.usecases.AuthUseCases
import com.rohit.ecom.domain.auth.usecases.LoginUseCase
import com.rohit.ecom.domain.auth.usecases.RegisterUseCase
import com.rohit.ecom.domain.cart.repository.CartRepository
import com.rohit.ecom.domain.cart.usecases.AddToCartUseCase
import com.rohit.ecom.domain.cart.usecases.CartUseCases
import com.rohit.ecom.domain.cart.usecases.GetAllCartItemUseCase
import com.rohit.ecom.domain.manager.tokenmanager.TokenPrefImpl
import com.rohit.ecom.domain.manager.tokenmanager.repository.TokenPref
import com.rohit.ecom.domain.products.repository.ProductsRepository
import com.rohit.ecom.domain.products.usecases.GetProductById
import com.rohit.ecom.domain.products.usecases.GetProducts
import com.rohit.ecom.domain.products.usecases.ProductsUseCases
import com.rohit.ecom.domain.profile.repository.ProfileRepository
import com.rohit.ecom.domain.profile.usecases.GetProfileUseCase
import com.rohit.ecom.domain.profile.usecases.ProfileUseCases
import com.rohit.ecom.domain.profile.usecases.UpdateProfileUseCase
import com.rohit.ecom.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
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
    fun provideCartApi(retrofit: Retrofit): CartApi {
        return retrofit.create(CartApi::class.java)
    }

    @Provides
    @Singleton
    fun providesProfileApi(retrofit: Retrofit): ProfileApi{
        return retrofit.create(ProfileApi::class.java)
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
    fun provideCartRepository(cartApi: CartApi): CartRepository{
        return CartRepositoryImpl(cartApi)
    }

    @Provides
    @Singleton
    fun  provideProfileRepository(profileApi : ProfileApi): ProfileRepository{
        return ProfileRepositoryImpl(profileApi);
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

    @Provides
    @Singleton
    fun provideCartUseCases(
        cartRepository: CartRepository
    ) : CartUseCases {
        return CartUseCases(
            addToCartUseCase = AddToCartUseCase(cartRepository),
            getAllCartItemUseCase = GetAllCartItemUseCase(cartRepository)
        )
    }

    @Provides
    @Singleton
    fun provideProfileUseCases(
        profileRepository: ProfileRepository
    ): ProfileUseCases {
        return ProfileUseCases(
            updateProfileUseCase = UpdateProfileUseCase(profileRepository),
            getProfileUseCase = GetProfileUseCase(profileRepository)
        )
    }

}
