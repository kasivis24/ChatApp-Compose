package com.mobile.chatapp
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.mobile.chatapp.data.model.RouteChatData
import com.mobile.chatapp.data.remote.repo.FirebaseRepository
import com.mobile.chatapp.data.remote.state.DbEventState
import com.mobile.chatapp.persentation.navigation.appnav.AppNavigation
import com.mobile.chatapp.persentation.ui.screen.duo.profile.ProfileScreen
import com.mobile.chatapp.persentation.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val firebaseRepo by lazy{
        FirebaseRepository()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    AppNavigation(navController)
                }
            }

        }
    }

    override fun onStop() {
        super.onStop()
        Log.d("Online-Cycle","Called Stop")
        lifecycleScope.launch (Dispatchers.IO){
            val userId = FirebaseAuth.getInstance().currentUser?.uid
            if (userId != null) {
                val result = firebaseRepo.removeActiveStatus(userId)
                when (result) {
                    is DbEventState.Success -> Log.d("onStop", result.message)
                    is DbEventState.Error -> Log.e("onStop", result.message)
                    else -> Unit
                }
            }
        }
    }
    @OptIn(DelicateCoroutinesApi::class)
    override fun onDestroy() {
        super.onDestroy()
        Log.d("Online-Cycle","Called Destroy")
//        GlobalScope.launch(Dispatchers.IO) {
//            val userId = FirebaseAuth.getInstance().currentUser?.uid
//            if (userId != null) {
//                val result = firebaseRepo.removeActiveStatus(userId)
//                when (result) {
//                    is DbEventState.Success -> Log.d("onDestroy", result.message)
//                    is DbEventState.Error -> Log.e("onDestroy", result.message)
//                    else -> Unit
//                }
//            }
//        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Online-Cycle","Called Start")
        lifecycleScope.launch(Dispatchers.IO) {
            val userId = FirebaseAuth.getInstance().currentUser?.uid
            if (userId != null) {
                val result = firebaseRepo.putActiveStatus(userId)
                when (result) {
                    is DbEventState.Success -> Log.d("onStart", result.message)
                    is DbEventState.Error -> Log.e("onStart", result.message)
                    else -> Unit
                }
            }
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        ProfileScreen(rememberNavController(), routeChatData = RouteChatData("", ""),)
    }
}