package com.mobile.chatapp.data.remote.db
import android.net.Uri
import androidx.lifecycle.LiveData
import com.mobile.chatapp.data.dto.DuoChatData
import com.mobile.chatapp.data.dto.DuoFriendsData
import com.mobile.chatapp.data.dto.DuoRequestData
import com.mobile.chatapp.data.dto.MessageData
import com.mobile.chatapp.data.dto.ProfileData
import com.mobile.chatapp.data.dto.SearchData
import com.mobile.chatapp.data.dto.UserData
import com.mobile.chatapp.data.remote.state.DbEventState

interface Database {

    suspend fun addUser(userData: UserData) : DbEventState

    suspend fun updateProfileData(profileData: ProfileData,refId : String) : DbEventState

    suspend fun getProfileRef(profileData: ProfileData) : String

    suspend fun searchProfile(searchQuery : String,uId : String) : LiveData<List<SearchData>>

    suspend fun sendRequest(senderId : String, receiverId : String,day : String,date : String, time : String) : DbEventState

    suspend fun getRequests(uId : String) : LiveData<List<DuoRequestData>>

    suspend fun declineRequest(requestId : String) : DbEventState

    suspend fun acceptRequest(duoChatData: DuoChatData) : DbEventState

    suspend fun getMyFriends(uId : String) : LiveData<List<DuoFriendsData>>

    suspend fun uploadProfileImage(uri: Uri,onSuccessImage : (String)-> Unit) : String

    suspend fun sendMessage(messageData: MessageData) : DbEventState

    suspend fun getDuoMessage(senderId: String,receiverId: String) : LiveData<List<MessageData>>

    suspend fun putActiveStatus(uId: String) : DbEventState

    suspend fun removeActiveStatus(uId: String) : DbEventState

    suspend fun changeCurrentChatId(uId: String, chatId : String, refId: String) : DbEventState

    suspend fun isChatIdActive(uId: String) : Boolean
}