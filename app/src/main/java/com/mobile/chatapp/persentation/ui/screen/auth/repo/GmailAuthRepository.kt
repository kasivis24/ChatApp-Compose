package com.mobile.chatapp.persentation.ui.screen.auth.repo

import com.google.firebase.auth.FirebaseAuth
import com.mobile.chatapp.persentation.ui.screen.auth.state.AuthUiState
import kotlinx.coroutines.tasks.await

class GmailAuthRepository : AuthRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override suspend fun signUp(email: String, password: String): AuthUiState {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            AuthUiState.Success(result.user?.uid ?: "Unknown UID")
        } catch (e: Exception) {
            AuthUiState.Error(e.localizedMessage ?: "Signup failed")
        }
    }

    override suspend fun logIn(email: String, password: String): AuthUiState {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            AuthUiState.Success(result.user?.uid ?: "Unknown UID")
        } catch (e: Exception) {
            AuthUiState.Error(e.localizedMessage ?: "Signup failed")
        }
    }
}
