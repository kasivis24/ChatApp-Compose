package com.mobile.chatapp.persentation.navigation.bottomnav

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mobile.chatapp.persentation.ui.screen.duo.DuoScreen
import com.mobile.chatapp.persentation.ui.screen.notify.NotifyScreen
import com.mobile.chatapp.persentation.ui.screen.settings.SettingsScreen
import com.mobile.chatapp.persentation.ui.screen.team.TeamScreen

@Composable
fun BottomNavigation(navHostController: NavHostController,navController: NavController){
    NavHost(navController = navHostController, startDestination = BottomNavItem.Duo.route){
        composable(BottomNavItem.Duo.route) {
            DuoScreen(navController)
        }

        composable(BottomNavItem.Team.route) {
            TeamScreen(navController)
        }

        composable(BottomNavItem.Settings.route) {
            SettingsScreen(navController)
        }

        composable(BottomNavItem.Notify.route) {
            NotifyScreen(navController)
        }
    }
}