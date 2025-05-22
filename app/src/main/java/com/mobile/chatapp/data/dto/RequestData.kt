package com.mobile.chatapp.data.dto

data class RequestData(
    val requestId : String,
    val senderId : String,
    val receiverId : String,
    val day : String,
    val date : String,
    val time : String,
)