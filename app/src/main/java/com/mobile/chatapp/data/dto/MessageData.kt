package com.mobile.chatapp.data.dto
import com.google.firebase.Timestamp
import com.mobile.chatapp.data.type.MessageType

data class MessageData(
    val msgId : String = "",
    val senderId : String = "",
    val receiverId : String = "",
    val msgContent : String = "",
    val contentType : String = "",
    val date : String = "",
    val time : String = "",
    val replyMsgId : String = "",
    val replyToName : String = "",
    val replyContent : String = "",
    val timeStamp : Timestamp? = null
){
    fun determineMsgType(): MessageType {
        return when {
            contentType.startsWith("text/") -> MessageType.TEXT
            contentType.startsWith("image/") -> MessageType.IMAGE
            contentType.startsWith("audio/") -> MessageType.AUDIO
            contentType.startsWith("video/") -> MessageType.VIDEO
            else -> MessageType.DOCUMENTS
        }
    }
}
