package com.mobile.chatapp.persentation.ui.screen.profilesetup

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobile.chatapp.R
import com.mobile.chatapp.data.dto.ProfileData
import com.mobile.chatapp.data.remote.state.DbEventState
import com.mobile.chatapp.persentation.components.SnackBarError
import com.mobile.chatapp.persentation.components.SnackBarSuccess
import com.mobile.chatapp.persentation.navigation.appnav.AppRoutes
import com.mobile.chatapp.persentation.ui.screen.auth.state.RegisterUiState
import com.mobile.chatapp.persentation.ui.screen.settings.viewmodel.SettingViewModel
import com.mobile.chatapp.persentation.ui.theme.AppTheme
import com.mobile.chatapp.persentation.ui.theme.zohoBold
import com.mobile.chatapp.persentation.ui.theme.zohoExtraBold
import com.mobile.chatapp.persentation.ui.theme.zohoMedium
import com.mobile.chatapp.persentation.ui.theme.zohoRegular
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun ProfileSetUp(navController: NavController,gmail : String,uId : String,settingViewModel: SettingViewModel = hiltViewModel()){


    Log.d("LogSetUp",uId);

    var name by rememberSaveable { mutableStateOf("") }

    var bio by rememberSaveable { mutableStateOf("") }

    val profileUiState by settingViewModel.uiProfileSetUpState.collectAsState()

    val snackbarHostState = SnackbarHostState()

    val coroutineScope = rememberCoroutineScope()

    Scaffold (
        snackbarHost = {
            when (profileUiState){
            is DbEventState.Success -> SnackBarSuccess(snackbarHostState)
            is DbEventState.Error -> SnackBarError(snackbarHostState)
            else -> Unit
        } }
    ){
        Box(Modifier.fillMaxSize()){

            Column (Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){

                Text(
                    text = "Profile Setup",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontFamily = zohoBold,
                    ),
                    modifier = Modifier.padding(vertical = 30.dp)
                )

                Box(Modifier.size(150.dp).clip(CircleShape).background(color = MaterialTheme.colorScheme.surfaceContainerHighest), contentAlignment = Alignment.Center){


                    Icon(modifier = Modifier.size(50.dp), painter = painterResource(R.drawable.baseline_add_a_photo_24), contentDescription = "Add-Photo")


                }

                Spacer(Modifier.height(30.dp))


                Column (Modifier.fillMaxWidth().padding(horizontal = 10.dp,)){
                    Text(
                        "Name",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = zohoRegular,
                        ),
                    )



                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp)
                    ){

                        OutlinedTextField(
                            textStyle = TextStyle(
                                fontFamily = zohoRegular,
                                fontSize = 14.sp,
                            ),
                            placeholder = {
                                Text("Ex: Mr.Charles",
                                    fontSize = 13.sp,
                                    fontFamily = zohoRegular,
                                    color = Color.Gray.copy(alpha = 0.7f),
                                    fontWeight = FontWeight.W500,
                                    textAlign = TextAlign.Center,
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            value = name,
                            onValueChange = {
                                name = it
                            },
                            shape = RoundedCornerShape(10.dp),
                            singleLine = true,
                            maxLines = 1,
                            colors = OutlinedTextFieldDefaults.colors(
//                            focusedContainerColor = Color.Gray.copy(alpha = 0.5f),
                                focusedBorderColor = Color.Gray.copy(alpha = 0.5f),
//                            disabledContainerColor = Color.Gray.copy(alpha = 0.5f),
                                disabledBorderColor = Color.Gray.copy(alpha = 0.5f),
//                            cursorColor = if (isDark) AppThemeColor else Color.Red,
                            ),

                            )

                    }

                }


                Column (Modifier.fillMaxWidth().padding(horizontal = 10.dp,)){
                    Text(
                        "Bio",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = zohoRegular,
                        ),
                    )



                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp)
                    ){

                        OutlinedTextField(
                            textStyle = TextStyle(
                                fontFamily = zohoRegular,
                                fontSize = 14.sp,
                            ),
                            placeholder = {
                                Text("Ex : I am Bussy !!!",
                                    fontSize = 13.sp,
                                    fontFamily = zohoRegular,
                                    color = Color.Gray.copy(alpha = 0.7f),
                                    fontWeight = FontWeight.W500,
                                    textAlign = TextAlign.Center,
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            value = bio,
                            onValueChange = {
                                bio = it
                            },
                            shape = RoundedCornerShape(10.dp),
                            singleLine = true,
                            maxLines = 1,
                            colors = OutlinedTextFieldDefaults.colors(
//                            focusedContainerColor = Color.Gray.copy(alpha = 0.5f),
                                focusedBorderColor = Color.Gray.copy(alpha = 0.5f),
//                            disabledContainerColor = Color.Gray.copy(alpha = 0.5f),
                                disabledBorderColor = Color.Gray.copy(alpha = 0.5f),
//                            cursorColor = if (isDark) AppThemeColor else Color.Red,
                            ),

                            )

                    }

                }



                Column (Modifier.fillMaxWidth().padding(horizontal = 10.dp,)){
                    Text(
                        "Gmail",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = zohoRegular,
                        ),
                    )



                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp)
                    ){

                        OutlinedTextField(
                            textStyle = TextStyle(
                                fontFamily = zohoRegular,
                                fontSize = 14.sp,
                            ),
                            readOnly = true,
                            placeholder = {
                                Text("$gmail",
                                    fontSize = 13.sp,
                                    fontFamily = zohoRegular,
                                    color = Color.Gray.copy(alpha = 0.7f),
                                    fontWeight = FontWeight.W500,
                                    textAlign = TextAlign.Center,
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            value = name,
                            onValueChange = {
                                name = it
                            },
                            shape = RoundedCornerShape(10.dp),
                            singleLine = true,
                            maxLines = 1,
                            colors = OutlinedTextFieldDefaults.colors(
//                            focusedContainerColor = Color.Gray.copy(alpha = 0.5f),
                                focusedBorderColor = Color.Gray.copy(alpha = 0.5f),
//                            disabledContainerColor = Color.Gray.copy(alpha = 0.5f),
                                disabledBorderColor = Color.Gray.copy(alpha = 0.5f),
//                            cursorColor = if (isDark) AppThemeColor else Color.Red,
                            ),

                        )

                    }

                }


                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(56.dp),
                        onClick = {
                            settingViewModel.profileSetUp(ProfileData(uId,"",name,gmail,bio))
                        },
                        shape = RoundedCornerShape(10.dp),
                    ){

                        when (val state = profileUiState){
                            is DbEventState.Idle -> Text(
                                "Finish",
                                fontSize = 18.sp,
                                style = TextStyle(fontFamily = zohoMedium,)
                            )
                            is DbEventState.Loading -> CircularProgressIndicator(
                                modifier = Modifier.size(18.dp),
                                color = Color.White,
                                strokeWidth = 2.5.dp,
                            )
                            is DbEventState.Success -> {
                                Text(
                                    "Finish",
                                    fontSize = 18.sp,
                                    style = TextStyle(fontFamily = zohoMedium,)
                                )
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Profile Setup Successfully !!!")
                                    Log.d("LogAuth","Succes Gmail Login")
                                    navController.navigate(AppRoutes.HOME_SCREEN)
                                    settingViewModel.reSetUiState()
                                }

                            }
                            is DbEventState.Error -> {

                                Text(
                                    "Finish",
                                    fontSize = 18.sp,
                                    style = TextStyle(fontFamily = zohoMedium,)
                                )

                                Log.d("LogAuth","Failed Gmail Login")
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Something went wrong!  Please check !!!")
                                    settingViewModel.reSetUiState()
                                }
                            }
                            else -> Unit
                        }




                    }
                }


            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewProfileSetUpLight(){
    AppTheme {
        val navController = rememberNavController()
        ProfileSetUp(navController,"","")
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewProfileSetUpDark(){
    AppTheme {
        val navController = rememberNavController()
        ProfileSetUp(navController,"","")
    }
}