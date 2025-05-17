package com.mobile.chatapp.persentation.ui.screen.auth.state


sealed class ForgetPasswordUiState {
    object Idle : ForgetPasswordUiState()
    object Loading : ForgetPasswordUiState()
    data class Success(val userId: String) : ForgetPasswordUiState()
    data class Error(val message: String) : ForgetPasswordUiState()
}
