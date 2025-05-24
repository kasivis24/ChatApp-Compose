package com.mobile.chatapp.data.dto

data class DuoFriendsData(
    val imgUrl: String = "",
    val friendName: String = "",
    val friendMail: String = "",
    val friendId: String = "",
    val friendStatus: Boolean = true,
    val lastMsg: String = "",
    val lastMsgFrom: String = "",
    val lastMsgTime: String = "",
    val unreadMsg: Int = 5
)