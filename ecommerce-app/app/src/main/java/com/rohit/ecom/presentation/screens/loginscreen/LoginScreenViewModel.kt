package com.rohit.ecom.presentation.screens.loginscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rohit.ecom.data.remote.auth.dto.LoginRequestDto
import com.rohit.ecom.data.remote.auth.dto.LoginResponseDto
import com.rohit.ecom.domain.auth.usecases.AuthUseCases
import com.rohit.ecom.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel(){

    private val _uiState = MutableStateFlow<ApiResult<LoginResponseDto>?>(null)

    val uiState = _uiState.asStateFlow()

    fun login(loginRequestDto: LoginRequestDto){
        viewModelScope.launch {
            authUseCases.login(loginRequestDto).collect { result ->
                _uiState.value = result
            }

        }
    }

}