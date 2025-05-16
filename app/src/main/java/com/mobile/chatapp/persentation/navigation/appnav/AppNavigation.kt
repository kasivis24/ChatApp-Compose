package com.mobile.chatapp.persentation.navigation.appnav
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mobile.chatapp.persentation.ui.screen.auth.login.LoginScreen
import com.mobile.chatapp.persentation.ui.screen.auth.register.RegisterScreen
import com.mobile.chatapp.persentation.ui.screen.home.HomeScreen
import com.mobile.chatapp.persentation.ui.screen.splash.SplashScreen

@Composable
fun AppNavigation(navHostController: NavHostController){
    NavHost(navController = navHostController, startDestination = AppRoutes.SPLASH_SCREEN){

        composable(AppRoutes.SPLASH_SCREEN) {
            SplashScreen(navHostController)
        }

        composable(AppRoutes.HOME_SCREEN) {
            HomeScreen(navHostController)
        }

        composable(AppRoutes.LOGIN_SCREEN) {
            LoginScreen(navHostController)
        }

        composable(AppRoutes.REGISTER_SCREEN) {
            RegisterScreen(navHostController)
        }
    }
}