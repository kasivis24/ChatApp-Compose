package com.mobile.chatapp.persentation.ui.screen.duo.search

sealed class RequestUiState {
    object Idle : RequestUiState()
    object Loading : RequestUiState()
    data class Success(val msg: String) : RequestUiState()
    data class Error(val message: String) : RequestUiState()
}
