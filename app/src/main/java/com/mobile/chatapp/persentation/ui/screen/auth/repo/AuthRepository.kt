package com.mobile.chatapp.persentation.ui.screen.auth.repo

import com.mobile.chatapp.persentation.ui.screen.auth.state.AuthUiState

interface AuthRepository {
    suspend fun signUp(email: String, password: String): AuthUiState
    suspend fun logIn(email: String, password: String): AuthUiState
}
