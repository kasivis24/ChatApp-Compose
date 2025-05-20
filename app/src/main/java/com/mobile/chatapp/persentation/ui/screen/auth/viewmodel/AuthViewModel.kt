package com.mobile.chatapp.persentation.ui.screen.auth.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.chatapp.data.remote.db.Database
import com.mobile.chatapp.persentation.ui.screen.auth.repo.AuthRepository
import com.mobile.chatapp.persentation.ui.screen.auth.state.ForgetPasswordUiState
import com.mobile.chatapp.persentation.ui.screen.auth.state.LoginUiState
import com.mobile.chatapp.persentation.ui.screen.auth.state.RegisterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiRegisterState = MutableStateFlow<RegisterUiState>(RegisterUiState.Idle)
    val uiRegisterState: StateFlow<RegisterUiState> = _uiRegisterState

    private val _uiLoginState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiLoginState: StateFlow<LoginUiState> = _uiLoginState

    private val _uiForgetState = MutableStateFlow<ForgetPasswordUiState>(ForgetPasswordUiState.Idle)
    val uiForgetState: StateFlow<ForgetPasswordUiState> = _uiForgetState

    fun signUp(email : String,password : String) {
        viewModelScope.launch {
            _uiRegisterState.value = RegisterUiState.Loading
            _uiRegisterState.value = authRepository.signUp(email,password)
        }
    }

    fun logIn(email: String,password: String) {
        viewModelScope.launch {
            _uiLoginState.value = LoginUiState.Loading
            _uiLoginState.value = authRepository.logIn(email, password)
        }
    }

    fun forgetPassword(email: String){
        viewModelScope.launch {
            _uiForgetState.value = ForgetPasswordUiState.Loading
            _uiForgetState.value = authRepository.forgetPassword(email)
        }
    }

    fun resetStateRegister() {
        _uiRegisterState.value = RegisterUiState.Idle
    }

    fun resetStateLogin() {
        _uiLoginState.value = LoginUiState.Idle
    }

    fun resetStateForget() {
        _uiForgetState.value = ForgetPasswordUiState.Idle
    }
}
