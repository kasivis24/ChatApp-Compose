package com.mobile.chatapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RouteChatData(
    val receiverId : String,
    val chatUserName : String,
) : Parcelable
