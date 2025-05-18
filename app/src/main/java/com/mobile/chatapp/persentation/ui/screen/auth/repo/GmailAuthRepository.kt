package com.mobile.chatapp.persentation.ui.screen.auth.repo

import com.google.firebase.auth.FirebaseAuth
import com.mobile.chatapp.data.dto.UserData
import com.mobile.chatapp.data.remote.db.Database
import com.mobile.chatapp.data.remote.repo.FirebaseFireStoreRepository
import com.mobile.chatapp.persentation.ui.screen.auth.state.ForgetPasswordUiState
import com.mobile.chatapp.persentation.ui.screen.auth.state.LoginUiState
import com.mobile.chatapp.persentation.ui.screen.auth.state.RegisterUiState
import kotlinx.coroutines.tasks.await

class GmailAuthRepository : AuthRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val database : Database = FirebaseFireStoreRepository()


    override suspend fun signUp(email: String, password: String): RegisterUiState {

        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()

            database.addUser(UserData(result.user?.uid ?: "Unknown UID",email))

            RegisterUiState.Success(result.user?.uid ?: "Unknown UID")
        } catch (e: Exception) {
            RegisterUiState.Error(e.localizedMessage ?: "Signup failed")
        }
    }

    override suspend fun logIn(email: String, password: String): LoginUiState {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            LoginUiState.Success(result.user?.uid ?: "Unknown UID")
        } catch (e: Exception) {
            LoginUiState.Error(e.localizedMessage ?: "Signup failed")
        }
    }

    override suspend fun forgetPassword(email: String): ForgetPasswordUiState {
        return try {
            val result = auth.sendPasswordResetEmail(email).await()
            ForgetPasswordUiState.Success( "Unknown UID")
        } catch (e: Exception) {
            ForgetPasswordUiState.Error(e.localizedMessage ?: "Send failed")
        }
    }
}
