package com.mobile.chatapp.persentation.ui.screen.auth.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.chatapp.persentation.ui.screen.auth.repo.AuthRepository
import com.mobile.chatapp.persentation.ui.screen.auth.state.AuthUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState

    fun signUp(email : String,password : String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            _uiState.value = authRepository.signUp(email,password)
        }
    }

    fun logIn(email: String,password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            _uiState.value = authRepository.logIn(email, password)
        }
    }

    fun resetState() {
        _uiState.value = AuthUiState.Idle
    }
}
