package com.mobile.chatapp.persentation.ui.screen.duo.search

sealed class SearchUiState {
    object Idle : SearchUiState()
    object Loading : SearchUiState()
    data class Success(val userId: String) : SearchUiState()
    data class Error(val message: String) : SearchUiState()
}
