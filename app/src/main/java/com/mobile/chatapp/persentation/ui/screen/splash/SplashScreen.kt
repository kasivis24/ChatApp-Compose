package com.mobile.chatapp.persentation.ui.screen.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.mobile.chatapp.persentation.navigation.appnav.AppRoutes

@Composable
fun SplashScreen(navController: NavController){

    val auth = FirebaseAuth.getInstance()


    if (auth.currentUser != null){
        navController.navigate(AppRoutes.HOME_SCREEN) {
            popUpTo(AppRoutes.SPLASH_SCREEN) { inclusive = true }
        }
    }else{
        navController.navigate(AppRoutes.REGISTER_SCREEN) {
            popUpTo(AppRoutes.SPLASH_SCREEN) { inclusive = true }
        }
    }
}