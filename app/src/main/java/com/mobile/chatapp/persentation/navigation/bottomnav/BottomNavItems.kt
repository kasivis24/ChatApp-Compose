package com.mobile.chatapp.persentation.navigation.bottomnav
import com.mobile.chatapp.R

sealed class BottomNavItem(val title: String, val icon: Int, val route: String) {
    object Duo : BottomNavItem("Duo", R.drawable.baseline_chat_24, "duo")
    object Team : BottomNavItem("Team", R.drawable.baseline_groups_24, "team")
    object Settings : BottomNavItem("Settings", R.drawable.baseline_settings_24, "settings")
}
