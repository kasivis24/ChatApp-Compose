package com.mobile.chatapp.persentation.ui.screen.auth.register

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.mobile.chatapp.persentation.components.SnackBarError
import com.mobile.chatapp.persentation.components.SnackBarSuccess
import com.mobile.chatapp.persentation.navigation.appnav.AppRoutes
import com.mobile.chatapp.persentation.ui.screen.auth.state.RegisterUiState
import com.mobile.chatapp.persentation.ui.screen.auth.viewmodel.AuthViewModel
import com.mobile.chatapp.persentation.ui.theme.AppTheme
import com.mobile.chatapp.persentation.ui.theme.zohoBold
import com.mobile.chatapp.persentation.ui.theme.zohoMedium
import com.mobile.chatapp.persentation.ui.theme.zohoRegular
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun RegisterScreen(navController: NavController,authViewModel: AuthViewModel = hiltViewModel()){

    var email by rememberSaveable { mutableStateOf("") }

    var password by rememberSaveable { mutableStateOf("") }

    var passwordSecure by rememberSaveable { mutableStateOf(false) }

    val uiState by authViewModel.uiRegisterState.collectAsState()

    val snackbarHostState = SnackbarHostState()

    val coroutineScope = rememberCoroutineScope()

    Scaffold (
        snackbarHost = {
            when (uiState){
                is RegisterUiState.Success -> SnackBarSuccess(snackbarHostState)
                is RegisterUiState.Error -> SnackBarError(snackbarHostState)
                else -> Unit
            }
        }
    ){

        Box(Modifier.fillMaxSize()){

            Column (Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){

                Image(modifier = Modifier.padding(vertical = 50.dp), painter = painterResource(R.drawable.img_auth), contentDescription = "")

                Box(Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp), contentAlignment = Alignment.CenterStart){
                    Text(
                        "Gmail Authentication",
                        fontSize = 25.sp,
                        style = TextStyle(
                            fontFamily = zohoBold,
                        )
                    )
                }

                Box(Modifier
                    .fillMaxWidth()
                    .padding(10.dp), contentAlignment = Alignment.CenterStart){
                    Text(
                        "Sign up with the gmail and password to secure your every sensitive info",
                        fontSize = 17.sp,
                        color = Color.Gray.copy(alpha = 0.5f),
                        style = TextStyle(
                            fontFamily = zohoMedium,
                        )
                    )
                }


                Column (Modifier.padding(10.dp)){
                    Column {
                        Text(
                            "Email Address",
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
                                    fontSize = 14.sp,
                                    fontFamily = zohoRegular
                                ),
                                placeholder = {
                                    Text("guest@gmail.com",
                                        fontSize = 13.sp,
                                        fontFamily = zohoRegular,
                                        color = Color.Gray.copy(alpha = 0.7f),
                                        fontWeight = FontWeight.W500,
                                        textAlign = TextAlign.Center,
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                value = email,
                                onValueChange = {
                                    email = it
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



                    Column {
                        Text(
                            "Password",
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
                                visualTransformation = if (passwordSecure) PasswordVisualTransformation() else VisualTransformation.None,
                                placeholder = {
                                    Text("1234%$*&!",
                                        fontSize = 13.sp,
                                        fontFamily = zohoRegular,
                                        color = Color.Gray.copy(alpha = 0.7f),
                                        fontWeight = FontWeight.W500,
                                        textAlign = TextAlign.Center,
                                    )
                                },
                                trailingIcon = {
                                    IconButton(
                                        onClick = {
                                            passwordSecure = !passwordSecure
                                        }) {
                                        Icon(
                                            if (passwordSecure) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                            contentDescription = null,
                                        )
                                    }
                                },
                                modifier = Modifier.fillMaxWidth(),
                                value = password,
                                onValueChange = {
                                    password = it
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

                    Spacer(Modifier.height(5.dp))

                    Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                        Text(
                            "Already have a account ? ",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontFamily = zohoMedium,
                            ),
                        )

                        Text(
                            " Sign In",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontFamily = zohoMedium,
                                color = MaterialTheme.colorScheme.primary
                            ),
                        )

                    }






                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd

                    ){
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            onClick = {
                                authViewModel.signUp(email, password)
                            },
                            shape = RoundedCornerShape(10.dp),
                        ) {

                            when (uiState){
                                is RegisterUiState.Idle -> Text(
                                    "Register",
                                    fontSize = 18.sp,
                                    style = TextStyle(
                                        fontFamily = zohoMedium,
                                    )
                                )
                                is RegisterUiState.Loading -> CircularProgressIndicator(
                                    modifier = Modifier.size(18.dp),
                                    color = Color.White,
                                    strokeWidth = 2.5.dp,
                                )
                                is RegisterUiState.Success -> {
                                    Text(
                                        "Register",
                                        fontSize = 18.sp,
                                        style = TextStyle(
                                            fontFamily = zohoMedium,
                                        )
                                    )
                                    coroutineScope.launch {
                                        snackbarHostState.showSnackbar("Welcome back! You’re signed in.")
                                        Log.d("LogAuth","Succes Gmail Login")
                                        navController.navigate(AppRoutes.HOME_SCREEN)
                                        authViewModel.resetStateRegister()
                                    }

                                }
                                is RegisterUiState.Error -> {

                                    Text(
                                        "Register",
                                        fontSize = 18.sp,
                                        style = TextStyle(fontFamily = zohoMedium)
                                    )

                                    Log.d("LogAuth","Failed Gmail Login")
                                    coroutineScope.launch {
                                        snackbarHostState.showSnackbar("Something went wrong!  Please check !!!")
                                        authViewModel.resetStateRegister()
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
}


@Preview(showBackground = true)
@Composable
fun PreviewRegister(){
    AppTheme {
        val navController = rememberNavController()
        RegisterScreen(navController)
    }
}