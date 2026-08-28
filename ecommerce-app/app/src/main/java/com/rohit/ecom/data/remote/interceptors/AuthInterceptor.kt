package com.rohit.ecom.data.remote.interceptors

import com.rohit.ecom.domain.manager.tokenmanager.repository.TokenPref
import com.rohit.ecom.utils.ApiEndpoints
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenPref: TokenPref
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val url = originalRequest.url.toString()

        // Check if the request URL contains any of the authenticated endpoints
        val isProtected = ApiEndpoints.AUTHENTICATED_ENDPOINTS.any { endpoint ->
            url.contains(endpoint)
        }

        if (isProtected) {
            // Read token from DataStore (runBlocking is acceptable here as intercept is called on background threads)
            val token = runBlocking {
                tokenPref.getRefreshToken()
            }

            if (!token.isNullOrEmpty()) {
                val authenticatedRequest = originalRequest.newBuilder()
                    .header("Authorization", "Bearer $token")
                    .build()
                return chain.proceed(authenticatedRequest)
            }
        }

        return chain.proceed(originalRequest)
    }
}
