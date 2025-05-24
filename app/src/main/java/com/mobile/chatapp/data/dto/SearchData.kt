package com.mobile.chatapp.data.dto

import com.mobile.chatapp.data.type.SearchStatusCode

data class SearchData(
    val userId : String = "",
    val imageUrl : String = "",
    val mail : String = "",
    val name : String = "",
    val bio : String = "",
    val statusCode : Int = 0
){
    fun determineSearchStatusCode(): SearchStatusCode {
        return when {
            statusCode.equals(1) -> SearchStatusCode.IN_REQUEST
            statusCode.equals(2) -> SearchStatusCode.ADD
            statusCode.equals(3) -> SearchStatusCode.PENDING
            statusCode.equals(4) -> SearchStatusCode.MESSAGE
            else -> SearchStatusCode.NONE
        }
    }
}
