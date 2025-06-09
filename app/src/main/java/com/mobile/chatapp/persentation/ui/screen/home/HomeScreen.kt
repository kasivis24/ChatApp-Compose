package com.mobile.chatapp.persentation.ui.screen.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mobile.chatapp.persentation.navigation.bottomnav.BottomNavItem
import com.mobile.chatapp.persentation.navigation.bottomnav.BottomNavigation
import com.mobile.chatapp.persentation.ui.theme.AppTheme
import com.mobile.chatapp.persentation.ui.theme.zohoBold

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    bottomNavController: NavHostController
) {
    Log.d("Recompose", "HomeScreen")

    Text("Home Screens ")

    Scaffold(
        bottomBar = {
            BottomNavBar(bottomNavController)
        }
    ) {
        BottomNavigation(bottomNavController, navController)
    }
}

@Composable
fun BottomNavBar(navController: NavController) {
    Log.d("Recompose", "BottomNavBar")

    val items = listOf(
        BottomNavItem.Duo,
        BottomNavItem.Team,
        BottomNavItem.Notify,
        BottomNavItem.Settings,
    )

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(
                        item.title,
                        style = TextStyle(fontFamily = zohoBold)
                    )
                },
                selected = currentDestination?.route == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                    selectedTextColor = MaterialTheme.colorScheme.onSurface,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                    indicatorColor = MaterialTheme.colorScheme.secondaryContainer
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    AppTheme {
        val navController = rememberNavController()
        val bottomNavController = rememberNavController()
        HomeScreen(navController, bottomNavController)
    }
}
