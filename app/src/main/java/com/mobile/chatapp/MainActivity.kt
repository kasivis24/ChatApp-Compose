package com.mobile.chatapp
import MessageTail
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.mobile.chatapp.persentation.navigation.appnav.AppNavigation
import com.mobile.chatapp.persentation.ui.screen.chat.ChatScreen
import com.mobile.chatapp.persentation.ui.screen.duo.profile.ProfileScreen
import com.mobile.chatapp.persentation.ui.theme.AppTheme
import com.mobile.chatapp.persentation.ui.screen.home.HomeScreen
import com.mobile.chatapp.persentation.ui.screen.settings.profilesetup.ProfileSetUp
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
//                    AppNavigation(navController)
////                    ProfileSetUp(navController,"","","")
////                    ProfileScreen()
////                    ProfileSetUp()
////                    ChatScreen()
////                    ChatScreenTest()
                    MessageTail("",true)
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        ProfileScreen()
    }
}