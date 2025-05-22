package com.mobile.chatapp.persentation.ui.screen.auth.repo

import com.mobile.chatapp.persentation.ui.screen.auth.state.ForgetPasswordUiState
import com.mobile.chatapp.persentation.ui.screen.auth.state.LoginUiState
import com.mobile.chatapp.persentation.ui.screen.auth.state.RegisterUiState


interface AuthRepository {
    suspend fun signUp(email: String, password: String): RegisterUiState
    suspend fun logIn(email: String, password: String): LoginUiState
    suspend fun forgetPassword(email: String): ForgetPasswordUiState
    fun getAuthToken(): String
}
