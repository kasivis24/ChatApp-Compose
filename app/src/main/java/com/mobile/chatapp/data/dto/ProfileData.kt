package com.mobile.chatapp.data.dto

data class ProfileData(
    val userId : String,
    val imageUrl : String = "",
    val name : String,
    val mail : String,
    val bio : String,
)
