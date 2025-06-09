package com.mobile.chatapp.persentation.ui.screen.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.mobile.chatapp.persentation.navigation.appnav.AppRoutes
import com.mobile.chatapp.persentation.ui.screen.auth.viewmodel.AuthViewModel
import com.mobile.chatapp.persentation.ui.screen.duo.DuoViewModel
@Composable
fun SplashScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel(),
    duoViewModel: DuoViewModel = hiltViewModel()
) {
    val auth = FirebaseAuth.getInstance()

    LaunchedEffect(key1 = true) {
        if (auth.currentUser != null) {
            navController.navigate(AppRoutes.HOME_SCREEN) {
                popUpTo(AppRoutes.SPLASH_SCREEN) { inclusive = true }
            }
        } else {
            navController.navigate(AppRoutes.REGISTER_SCREEN) {
                popUpTo(AppRoutes.SPLASH_SCREEN) { inclusive = true }
            }
        }
    }
}
