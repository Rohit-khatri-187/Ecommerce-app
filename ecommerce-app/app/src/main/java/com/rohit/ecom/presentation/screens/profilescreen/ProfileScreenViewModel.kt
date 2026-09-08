package com.rohit.ecom.presentation.screens.profilescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rohit.ecom.data.remote.profile.dto.ProfileResponse
import com.rohit.ecom.data.remote.profile.dto.ProfileUpdateDto
import com.rohit.ecom.domain.manager.tokenmanager.repository.TokenPref
import com.rohit.ecom.domain.profile.usecases.ProfileUseCases
import com.rohit.ecom.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val profileUseCases: ProfileUseCases,
    private val tokenPref: TokenPref
) : ViewModel() {

    private val _getProfileUiState = MutableStateFlow<ApiResult<ProfileResponse>>(ApiResult.Loading)
    val getProfileUiState = _getProfileUiState.asStateFlow()

    private val _updateProfileState = MutableStateFlow<ApiResult<ProfileResponse>?>(null)
    val updateProfileState = _updateProfileState.asStateFlow()

    init {
        getProfile()
    }

    fun getProfile() {
        viewModelScope.launch {
            profileUseCases.getProfileUseCase().collect { result ->
                _getProfileUiState.value = result
            }
        }
    }

    fun updateProfile(updateProfileRequest: ProfileUpdateDto) {
        viewModelScope.launch {
            profileUseCases.updateProfileUseCase(updateProfileRequest).collect { result ->
                _updateProfileState.value = result
                //  Update the main profile state directly if update is successful
                if (result is ApiResult.Success) {
                    _getProfileUiState.value = ApiResult.Success(result.data)
                }
            }
        }
    }

    fun resetUpdateState() {
        _updateProfileState.value = null
    }

    fun logout(onLogoutSuccess: () -> Unit) {
        viewModelScope.launch {
            tokenPref.clearRefreshToken()
            onLogoutSuccess()
        }
    }
}
