package com.mobile.android_chatapp.ui.theme.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mobile.android_chatapp.ui.theme.screens.AuthScreen
import com.mobile.android_chatapp.ui.theme.screens.DuoScreen
import com.mobile.android_chatapp.ui.theme.screens.SettingsScreen
import com.mobile.android_chatapp.ui.theme.screens.TeamScreen

@Composable
fun AppNavigation(navHostController: NavHostController){
    NavHost(navController = navHostController, startDestination = AppRoutes.AUTH_SCREEN){
        composable(BottomNavItem.Duo.route) {
            DuoScreen(navHostController)
        }

        composable(BottomNavItem.Team.route) {
            TeamScreen(navHostController)
        }

        composable(BottomNavItem.Settings.route) {
            SettingsScreen(navHostController)
        }

        composable(AppRoutes.AUTH_SCREEN) {
            AuthScreen(navHostController)
        }
    }
}