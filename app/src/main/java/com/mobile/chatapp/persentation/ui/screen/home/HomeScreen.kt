package com.mobile.chatapp.persentation.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mobile.chatapp.persentation.ui.theme.AppTheme
import com.mobile.chatapp.persentation.navigation.bottomnav.BottomNavItem
import com.mobile.chatapp.persentation.navigation.appnav.AppNavigation
import com.mobile.chatapp.persentation.navigation.bottomnav.BottomNavigation
import com.mobile.chatapp.persentation.ui.theme.zohoBold


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController){

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavBar(navController)
        }
    ) {
        BottomNavigation(navController)
    }
}

@Composable
fun BottomNavBar(navController: NavController){
    val items = listOf(

        BottomNavItem.Duo,
        BottomNavItem.Team,
        BottomNavItem.Notify,
        BottomNavItem.Settings,
    )
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    NavigationBar {
        items.forEach { item ->


            NavigationBarItem(
                icon = { Icon(painter = painterResource(item.icon), contentDescription = item.title) },
                label = { Text(
                    item.title,
                    style = TextStyle(
                        fontFamily = zohoBold,
                    )
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
                colors = NavigationBarItemColors(
                    selectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                    selectedIndicatorColor = MaterialTheme.colorScheme.secondaryContainer,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                    selectedTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledIconColor = MaterialTheme.colorScheme.onSurface,
                    disabledTextColor = MaterialTheme.colorScheme.onSurface,
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview(){
    AppTheme  {
        val navController = rememberNavController()
        HomeScreen(navController)
    }
}