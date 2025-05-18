package com.mobile.chatapp.persentation.navigation.bottomnav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mobile.chatapp.persentation.ui.screen.duo.DuoScreen
import com.mobile.chatapp.persentation.ui.screen.notify.NotifyScreen
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

        composable(BottomNavItem.Notify.route) {
            NotifyScreen(navHostController)
        }
    }
}