package com.rohit.ecom.domain.manager.tokenmanager

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.rohit.ecom.domain.manager.tokenmanager.repository.TokenPref
import com.rohit.ecom.utils.TokenKeys
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class TokenPrefImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : TokenPref {
    override suspend fun getRefreshToken(): String? {
        return dataStore.data.first()[TokenKeys.REFRESH_TOKEN]
    }

    override suspend fun saveRefreshToken(token: String) {
        dataStore.edit {
            it[TokenKeys.REFRESH_TOKEN] = token
        }
    }

    override suspend fun clearRefreshToken() {
        dataStore.edit {
            it.remove(TokenKeys.REFRESH_TOKEN)
        }
    }
}