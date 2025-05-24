package com.mobile.chatapp.data.remote.db

import androidx.lifecycle.LiveData
import com.mobile.chatapp.data.dto.DuoChatData
import com.mobile.chatapp.data.dto.DuoFriendsData
import com.mobile.chatapp.data.dto.DuoRequestData
import com.mobile.chatapp.data.dto.ProfileData
import com.mobile.chatapp.data.dto.RequestData
import com.mobile.chatapp.data.dto.SearchData
import com.mobile.chatapp.data.dto.UserData
import com.mobile.chatapp.data.remote.state.DbEventState
import kotlinx.coroutines.flow.StateFlow

interface Database {

    suspend fun addUser(userData: UserData) : DbEventState

    suspend fun addProfileData(profileData: ProfileData) : DbEventState

    suspend fun searchProfile(searchQuery : String,uId : String) : LiveData<List<SearchData>>

    suspend fun sendRequest(senderId : String, receiverId : String,day : String,date : String, time : String) : DbEventState

    suspend fun getRequests(uId : String) : LiveData<List<DuoRequestData>>

    suspend fun declineRequest(requestId : String) : DbEventState

    suspend fun acceptRequest(duoChatData: DuoChatData) : DbEventState

    suspend fun getMyFriends(uId : String) : LiveData<List<DuoFriendsData>>
}