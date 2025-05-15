package com.mobile.chatapp.persentation.navigation.bottomnav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mobile.chatapp.persentation.navigation.appnav.AppRoutes
import com.mobile.chatapp.persentation.ui.screen.Duo.DuoScreen
import com.mobile.chatapp.persentation.ui.screen.auth.login.LoginScreen
import com.mobile.chatapp.persentation.ui.screen.auth.register.RegisterScreen
import com.mobile.chatapp.persentation.ui.screen.settings.SettingsScreen
import com.mobile.chatapp.persentation.ui.screen.team.TeamScreen

@Composable
fun BottomNavigation(navHostController: NavHostController){
    NavHost(navController = navHostController, startDestination = BottomNavItem.Duo.route){
        composable(BottomNavItem.Duo.route) {
            DuoScreen(navHostController)
        }

        composable(BottomNavItem.Team.route) {
            TeamScreen(navHostController)
        }

        composable(BottomNavItem.Settings.route) {
            SettingsScreen(navHostController)
        }
    }
}