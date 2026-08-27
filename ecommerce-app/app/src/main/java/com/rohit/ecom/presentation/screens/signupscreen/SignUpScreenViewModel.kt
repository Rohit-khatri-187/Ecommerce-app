package com.rohit.ecom.presentation.screens.signupscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rohit.ecom.data.remote.auth.dto.LoginRequestDto
import com.rohit.ecom.data.remote.auth.dto.RegisterRequestDto
import com.rohit.ecom.data.remote.auth.dto.RegisterResponseDto
import com.rohit.ecom.domain.auth.usecases.AuthUseCases
import com.rohit.ecom.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
): ViewModel() {

    private val _uiState = MutableStateFlow<ApiResult<RegisterResponseDto>?>(null)

    val uiState = _uiState.asStateFlow()

    fun register(registerRequestDto: RegisterRequestDto){
        viewModelScope.launch {
            authUseCases.register(registerRequestDto).collect { result ->
                _uiState.value = result
            }

        }
    }
    
}