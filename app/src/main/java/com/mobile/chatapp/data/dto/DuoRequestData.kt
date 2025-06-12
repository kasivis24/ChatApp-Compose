package com.mobile.chatapp.data.dto

data class DuoRequestData(
    val requestId : String = "",
    val senderId : String = "",
    val receiverId : String = "",
    val name : String = "",
    val mail : String = "",
    val day : String = "",
    val date : String = "",
    val time : String = "",
    val imageUrl : String = ""
)