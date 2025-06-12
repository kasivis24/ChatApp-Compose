package com.mobile.chatapp.persentation.navigation.appnav
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mobile.chatapp.data.model.RouteChatData
import com.mobile.chatapp.data.remote.db.Database
import com.mobile.chatapp.persentation.ui.screen.auth.login.LoginScreen
import com.mobile.chatapp.persentation.ui.screen.auth.register.RegisterScreen
import com.mobile.chatapp.persentation.ui.screen.chat.ChatScreen
import com.mobile.chatapp.persentation.ui.screen.duo.profile.ProfileScreen
import com.mobile.chatapp.persentation.ui.screen.duo.profile.ProfileScreenViewModel
import com.mobile.chatapp.persentation.ui.screen.duo.search.SearchScreen
import com.mobile.chatapp.persentation.ui.screen.home.HomeScreen
import com.mobile.chatapp.persentation.ui.screen.settings.profilesetup.ProfileSetUp
import com.mobile.chatapp.persentation.ui.screen.splash.SplashScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(navHostController: NavHostController){
    NavHost(navController = navHostController, startDestination = AppRoutes.SPLASH_SCREEN){

        composable(AppRoutes.SPLASH_SCREEN) {
            SplashScreen(navHostController)
        }

        composable(AppRoutes.HOME_SCREEN) {
            val bottomNavController = rememberNavController()
            HomeScreen(navHostController, bottomNavController)
        }

        composable(AppRoutes.LOGIN_SCREEN) {
            LoginScreen(navHostController)
        }

        composable(AppRoutes.REGISTER_SCREEN) {
            RegisterScreen(navHostController)
        }

        composable(AppRoutes.CHAT_SCREEN) { backStackEntry ->
            val routeChatData = remember {
                navHostController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<RouteChatData>("routeChatData")
            }

            routeChatData?.let {
                ChatScreen(
                    navController = navHostController,
                    routeChatData = it
                )
            }
        }


        composable("${AppRoutes.PROFILESETUP_SCREEN}/{gmail}/{uId}/{refId}",
            arguments = listOf(
                navArgument("gmail"){
                    type = NavType.StringType
                },
                navArgument("uId"){
                    type = NavType.StringType
                },
                navArgument("refId"){
                    type = NavType.StringType
                }
            )
            ) {
            ProfileSetUp(navHostController,it.arguments?.getString("refId") ?: "",it.arguments?.getString("gmail") ?: "",it.arguments?.getString("uId") ?: "")
        }

        composable(AppRoutes.SEARCH_SCREEN) {
            SearchScreen(navHostController)
        }

        composable(AppRoutes.PROFILE_SCREEN) { backStackEntry ->
            val routeChatData = remember {
                navHostController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<RouteChatData>("routeChatData")
            }

            routeChatData?.let {
                ProfileScreen(
                    navHostController = navHostController,
                    routeChatData = it,
                )
            }
        }
    }
}