package com.mobile.chatapp.utils

import android.content.Context
import androidx.compose.runtime.Composable

@Composable
fun toast(msg : String, context: Context){
    android.widget.Toast.makeText(context, msg, android.widget.Toast.LENGTH_SHORT).show()
}