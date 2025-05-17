package com.mobile.chatapp.persentation.ui.screen.auth.state

sealed class RegisterUiState {
    object Idle : RegisterUiState()
    object Loading : RegisterUiState()
    data class Success(val userId: String) : RegisterUiState()
    data class Error(val message: String) : RegisterUiState()
}
