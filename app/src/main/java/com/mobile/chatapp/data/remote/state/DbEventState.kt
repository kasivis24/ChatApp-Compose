package com.mobile.chatapp.data.remote.state

sealed class DbEventState {
    object Idle : DbEventState()
    object Loading : DbEventState()
    data class Success(val message: String) : DbEventState()
    data class Error(val message: String) : DbEventState()
}
