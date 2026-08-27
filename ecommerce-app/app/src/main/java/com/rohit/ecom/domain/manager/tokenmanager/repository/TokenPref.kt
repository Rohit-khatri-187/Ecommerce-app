package com.rohit.ecom.domain.manager.tokenmanager.repository

interface TokenPref {
    suspend fun getRefreshToken(): String?

    suspend fun saveRefreshToken(token: String)

    suspend fun clearRefreshToken()

}