package com.mobile.chatapp.data.remote.db

import com.mobile.chatapp.data.dto.ProfileData
import com.mobile.chatapp.data.dto.UserData
import com.mobile.chatapp.data.remote.state.DbEventState

interface Database {

    suspend fun addUser(userData: UserData) : DbEventState

    suspend fun addProfileData(profileData: ProfileData) : DbEventState

}