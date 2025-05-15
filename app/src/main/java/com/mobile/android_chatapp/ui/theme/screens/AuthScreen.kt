package com.mobile.android_chatapp.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mobile.android_chatapp.R
import com.mobile.android_chatapp.ui.theme.zohoBold
import com.mobile.android_chatapp.ui.theme.zohoMedium
import com.mobile.android_chatapp.ui.theme.zohoRegular
import com.mobile.android_chatapp.ui.theme.zohoSemiBold

@Composable
fun AuthScreen(navController: NavController){


    var email by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }

    var passwordSecure by remember { mutableStateOf(false) }

    Box(Modifier.fillMaxSize()){

        Column (Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){

            Image(modifier = Modifier.padding(vertical = 40.dp), painter = painterResource(R.drawable.img_auth), contentDescription = "")

            Box(Modifier.fillMaxWidth().padding(horizontal = 10.dp), contentAlignment = Alignment.CenterStart){
                Text(
                    "Gmail Authentication",
                    fontSize = 25.sp,
                    style = TextStyle(
                        fontFamily = zohoBold,
                    )
                )
            }

            Box(Modifier.fillMaxWidth().padding(10.dp), contentAlignment = Alignment.CenterStart){
                Text(
                    "Enter the Gmail to Secure your sensitive Information ",
                    fontSize = 20.sp,
                    color = Color.Gray.copy(alpha = 0.4f),
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


                Spacer(Modifier.height(15.dp))


                Box(Modifier.fillMaxWidth().height(56.dp)){
                    Button(
                        modifier = Modifier.fillMaxSize(),onClick = {},
                        shape = RoundedCornerShape(10.dp)
                    ) {

                        Text(
                            "Login",
                            fontSize = 18.sp,
                            style = TextStyle(
                                fontFamily = zohoMedium,
                            )
                        )

                    }
                }





            }



        }

    }
}