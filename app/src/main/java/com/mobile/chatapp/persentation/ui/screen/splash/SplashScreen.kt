package com.mobile.chatapp.persentation.ui.screen.splash

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.mobile.chatapp.persentation.navigation.appnav.AppRoutes

@Composable
fun SplashScreen(navController: NavController){
    navController.navigate(AppRoutes.LOGIN_SCREEN)
}