package com.mobile.chatapp.data.remote.repo

import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import com.mobile.chatapp.data.dto.ActiveUsersData
import com.mobile.chatapp.data.dto.DuoChatData
import com.mobile.chatapp.data.dto.DuoFriendsData
import com.mobile.chatapp.data.dto.DuoRequestData
import com.mobile.chatapp.data.dto.MessageData
import com.mobile.chatapp.data.dto.ProfileData
import com.mobile.chatapp.data.dto.RequestData
import com.mobile.chatapp.data.dto.SearchData
import com.mobile.chatapp.data.dto.UserData
import com.mobile.chatapp.data.remote.db.Database
import com.mobile.chatapp.data.remote.state.DbEventState
import kotlinx.coroutines.tasks.await

class FirebaseRepository : Database {

    private val firebaseFireStore = FirebaseFirestore.getInstance()

    private val firebaseStorage = FirebaseStorage.getInstance()


    override suspend fun addUser(userData: UserData) : DbEventState {

        return try {
            firebaseFireStore.collection("userData").document()
                .set(userData).await()
            DbEventState.Success("Userdata Added Success")
        }
        catch (e : Exception){
            DbEventState.Error("Something went wrong")
        }
    }

    override suspend fun updateProfileData(profileData: ProfileData,refId : String): DbEventState {
        return try {
            firebaseFireStore.collection("profileData").document(refId)
                .set(profileData).await()
            DbEventState.Success("ProfileData Added Success")
        }
        catch (e : Exception){
            DbEventState.Error("Something went wrong")
        }
    }

    override suspend fun getProfileRef(profileData: ProfileData): String {
        return try {
            val ref = firebaseFireStore.collection("profileData").document()
            val id = ref.id
            ref.set(profileData).await()
            id
        } catch (e: Exception) {
            ""
        }
    }


    override suspend fun searchProfile(searchQuery: String,uId : String): LiveData<List<SearchData>> {
        val _profilesFlow = MutableLiveData<List<SearchData>>(emptyList())

        try {


            firebaseFireStore
                .collection("requestData")
                .whereEqualTo("receiverId",uId)
                .addSnapshotListener { snapshotRequest, error ->
                    if (error != null){
                        return@addSnapshotListener
                    }


                    if (snapshotRequest != null){
                        val requests = snapshotRequest.documents.mapNotNull { it.toObject(RequestData::class.java) }


                        firebaseFireStore
                            .collection("requestData")
                            .whereEqualTo("senderId",uId)
                            .addSnapshotListener { snapahotRequestSender, error ->
                                if (error != null){
                                    return@addSnapshotListener
                                }
                                if (snapahotRequestSender != null){

                                    val requestSender = snapahotRequestSender.documents.mapNotNull { it.toObject(RequestData::class.java) }
                                    val requestSenderAndReceive = requests + requestSender

                                    Log.d("LogData","CombinedData ${requestSenderAndReceive}")


                                    firebaseFireStore
                                        .collection("profileData")
                                        .whereNotEqualTo("userId",uId)
                                        .addSnapshotListener { snapshotProfile, error ->
                                            if (error != null) {
                                                return@addSnapshotListener
                                            }
                                            if (snapshotProfile != null) {


                                                val profiles = snapshotProfile.documents.mapNotNull { it.toObject(ProfileData::class.java) }
                                                    .filter { profile ->
                                                        profile.name.contains(searchQuery, ignoreCase = true) || profile.mail.contains(searchQuery, ignoreCase = true)
                                                    }


                                                firebaseFireStore
                                                    .collection("duoChatData")
                                                    .addSnapshotListener { snapshotChat, error ->
                                                        if (error != null) {
                                                            return@addSnapshotListener
                                                        }
                                                        if (snapshotChat != null){

                                                            val duoChatData = snapshotChat.documents.mapNotNull { it.toObject(DuoChatData::class.java) }

                                                            val tempSearchedProfilesList = mutableStateListOf<SearchData>()

                                                            Log.d("LogData","Request LIst reciver -> ${requests}")
                                                            Log.d("LogData","Request LIst sendeer -> ${requestSender}")


                                                            profiles.forEach {profileTemp->
                                                                var occurs : Boolean = true
                                                                requestSenderAndReceive.forEach {requestTemp->
                                                                    if (profileTemp.userId.equals(requestTemp.senderId) && requestTemp.receiverId.equals(uId)){
                                                                        Log.d("LogData","Searched Data S1 -> ${SearchData(profileTemp.userId,profileTemp.imageUrl,profileTemp.mail,profileTemp.name,profileTemp.bio,1,requestTemp.requestId,uId,requestTemp.senderId)}")
                                                                        tempSearchedProfilesList.add(SearchData(profileTemp.userId,profileTemp.imageUrl,profileTemp.mail,profileTemp.name,profileTemp.bio,1,requestTemp.requestId,uId,requestTemp.senderId))
                                                                        occurs = false
                                                                    }else if (profileTemp.userId.equals(requestTemp.receiverId) && requestTemp.senderId.equals(uId)){
                                                                        Log.d("LogData", "Searched Data S3 -> ${SearchData(profileTemp.userId,profileTemp.imageUrl,profileTemp.mail,profileTemp.name,profileTemp.bio,3)}")
                                                                        tempSearchedProfilesList.add(SearchData(profileTemp.userId,profileTemp.imageUrl,profileTemp.mail,profileTemp.name,profileTemp.bio,3))
                                                                        occurs = false
                                                                    }
                                                                }

                                                                duoChatData.forEach {
                                                                    if (occurs && it.friendId.equals(profileTemp.userId) && it.userId.equals(uId)){
                                                                        Log.d("LogData", "Searched In chat UI -> ${SearchData(profileTemp.userId,profileTemp.imageUrl,profileTemp.mail,profileTemp.name,profileTemp.bio,4)}")
                                                                        tempSearchedProfilesList.add(SearchData(profileTemp.userId,profileTemp.imageUrl,profileTemp.mail,profileTemp.name,profileTemp.bio,4))
                                                                        occurs = false
                                                                    }
                                                                }
                                                                if (occurs){
                                                                    Log.d("LogData","Searched Data occurs -> ${SearchData(profileTemp.userId,profileTemp.imageUrl,profileTemp.mail,profileTemp.name,profileTemp.bio,2)}")
                                                                    tempSearchedProfilesList.add(SearchData(profileTemp.userId,profileTemp.imageUrl,profileTemp.mail,profileTemp.name,profileTemp.bio,2))
                                                                }
                                                            }

                                                            Log.d("LogData","Searched Data Last -> $tempSearchedProfilesList")
                                                            _profilesFlow.value = tempSearchedProfilesList
                                                            Log.d("LogData","Searched Data Last -> Return List${_profilesFlow.value}")

                                                        }
                                                    }

                                            }
                                        }



                                }
                            }
                    }
                }

            Log.d("LogData","Result -> ${_profilesFlow.value}")
        } catch (e: Exception) {
            e.printStackTrace()
            _profilesFlow.value = emptyList()
            Log.d("LogData","Failed -> $e")
        }

        return _profilesFlow
    }

    override suspend fun sendRequest(senderId : String,receiverId : String,day : String,date : String,time : String): DbEventState {

        return try {
            val ref = firebaseFireStore.collection("requestData").document()
            val id = ref.id
            ref.set(RequestData(id,senderId,receiverId,day, date, time)).await()
            DbEventState.Success("Request Send Success")
        }
        catch (e : Exception){
            DbEventState.Error("Something went wrong")
        }
    }

    override suspend fun getRequests(uId: String): LiveData<List<DuoRequestData>> {
        val _requestProfilesFlow = MutableLiveData<List<DuoRequestData>>(emptyList())


        Log.d("LogData","Result -> start $uId")

        try {

            firebaseFireStore
                .collection("requestData")
                .whereEqualTo("receiverId",uId)
                .addSnapshotListener { snapshot,error->
                    if (error != null) {
                        return@addSnapshotListener
                    }
                    if (snapshot != null){
                        val tempProfilesList = mutableStateListOf<DuoRequestData>()

                        val request = snapshot.documents.mapNotNull {it.toObject(RequestData::class.java)}
                        Log.d("LogData","Result Size ${request.size} -> ${request}")

                        if (request.isEmpty()) {
                            _requestProfilesFlow.value = emptyList()
                        }

                        request.forEach { profileData ->
                            firebaseFireStore.collection("profileData")
                                .whereEqualTo("userId",profileData.senderId)
                                .addSnapshotListener {it,error ->
                                    if (it != null){
                                        val requestProfiles = it.documents.mapNotNull { it.toObject(ProfileData::class.java)}

                                        request.forEach {requestDataForEach->
                                            requestProfiles.forEach {profileDataforEach->
                                                if (requestDataForEach.senderId.equals(profileDataforEach.userId) && requestDataForEach.receiverId.equals(uId)){
                                                    tempProfilesList.add(DuoRequestData(requestDataForEach.requestId,requestDataForEach.senderId,requestDataForEach.receiverId,profileDataforEach.name,profileDataforEach.mail,requestDataForEach.day,requestDataForEach.date,requestDataForEach.time, profileDataforEach.imageUrl))
                                                    Log.d("LogData","Data Notify list main -> ${DuoRequestData(requestDataForEach.requestId,requestDataForEach.senderId,requestDataForEach.receiverId,profileDataforEach.name,profileDataforEach.mail,requestDataForEach.day,requestDataForEach.date,requestDataForEach.time, profileDataforEach.imageUrl)}")
                                                }
                                            }
                                        }

                                        _requestProfilesFlow.value = tempProfilesList
                                        Log.d("LogData","List Dtaa ${requestProfiles.size} and ${requestProfiles}")
                                        Log.d("LogData","Result  For Notify Data  -> ${_requestProfilesFlow.value}")
                                    }
                            }
                        }
                    }
                }



        } catch (e: Exception) {
            e.printStackTrace()
            _requestProfilesFlow.value = emptyList()
            Log.d("LogData","Failed -> $e")
        }

        return _requestProfilesFlow
    }

    override suspend fun declineRequest(requestId: String): DbEventState {

        return try {
            firebaseFireStore
                .collection("requestData")
                .document(requestId)
                .delete()
            DbEventState.Success("Request Decline Success")
        }
        catch (e : Exception){
            DbEventState.Error("Something went wrong")
        }
    }

    override suspend fun acceptRequest(duoChatData: DuoChatData): DbEventState {
        return try {
            firebaseFireStore
                .collection("duoChatData")
                .document()
                .set(duoChatData)
                .await()
            DbEventState.Success("Request Send Success")
        }
        catch (e : Exception){
            DbEventState.Error("Something went wrong")
        }
    }



    override suspend fun getMyFriends(uId: String): LiveData<List<DuoFriendsData>> {

        val _myFriendsFlow = MutableLiveData<List<DuoFriendsData>>(emptyList())

        try {
            firebaseFireStore
                .collection("duoChatData")
                .whereEqualTo("userId", uId)
                .addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        return@addSnapshotListener
                    }
                    if (snapshot != null) {

                        val tempFriendsList = mutableStateListOf<DuoFriendsData>()

                        snapshot.documents.map {
                            val friendId = it.toObject(DuoChatData::class.java)?.friendId
                            if (friendId != null) {
                                firebaseFireStore
                                    .collection("profileData")
                                    .whereEqualTo("userId", friendId)
                                    .addSnapshotListener { it, error ->
                                        if (error != null){
                                            return@addSnapshotListener
                                        }
                                        if (it != null) {
                                            val friendsDataList = it.documents.mapNotNull { it.toObject(ProfileData::class.java) }
                                            friendsDataList.forEach {friendData ->
                                                tempFriendsList.add(
                                                    DuoFriendsData(
                                                        friendData?.imageUrl ?: "",
                                                        friendData?.name ?: "",
                                                        friendData?.mail ?: "",
                                                        friendId, true, "", "", "", 5
                                                    )
                                                )
                                            }
                                            _myFriendsFlow.value = tempFriendsList
                                            Log.d("LogData", "Friends list -> $tempFriendsList")
                                        }
                                    }
                            }
                        }
                    }
                }
        }
        catch (e : Exception){
            _myFriendsFlow.value = emptyList()
        }

        return _myFriendsFlow
    }


    override suspend fun uploadProfileImage(uri: Uri, onSuccessImage: (String) -> Unit): String {
        return try {
            val storageRef = firebaseStorage.reference
                .child("ChatAppStorage/${System.currentTimeMillis()}_${uri.lastPathSegment}")

            val uploadTask = storageRef.putFile(uri).await() // Wait for upload
            val downloadUrl = storageRef.downloadUrl.await() // Wait for URL
            onSuccessImage(downloadUrl.toString())
            downloadUrl.toString()
        } catch (e: Exception) {
            ""
        }
    }

    override suspend fun sendMessage(messageData: MessageData): DbEventState {
        return try {
            val ref = firebaseFireStore.collection("MessageData").document()
            val id = ref.id

            val data = hashMapOf(
                "msgId" to id,
                "senderId" to messageData.senderId,
                "receiverId" to messageData.receiverId,
                "msgContent" to messageData.msgContent,
                "contentType" to messageData.contentType,
                "date" to messageData.date,
                "time" to messageData.time,
                "replyMsgId" to messageData.replyMsgId,
                "replyToName" to messageData.replyToName,
                "replyContent" to messageData.replyContent,
                "timeStamp" to FieldValue.serverTimestamp()
            )

            ref.set(data).await()
            DbEventState.Success("Message Added Success")
        }
        catch (e : Exception){
            DbEventState.Error("Something went wrong")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getDuoMessage(senderId: String, receiverId: String): LiveData<List<MessageData>> {
        val _myMessageData = MutableLiveData<List<MessageData>>(emptyList())

        try {
            firebaseFireStore
                .collection("MessageData")
                .orderBy("timeStamp",Query.Direction.DESCENDING)
                .addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        return@addSnapshotListener
                    }
                    if (snapshot != null) {

                        val msgDataList = snapshot.documents.mapNotNull { it.toObject(MessageData::class.java) }

                        val tempMessageList = msgDataList.filter { msg ->
                            (msg.senderId == senderId && msg.receiverId == receiverId) || (msg.senderId == receiverId && msg.receiverId == senderId)
                        }



                        Log.d("LogData", "Message Data -> $tempMessageList")
                        _myMessageData.value = tempMessageList
                    }
                }
        }
        catch (e : Exception){
            _myMessageData.value = emptyList()
        }

        return _myMessageData
    }

    override suspend fun putActiveStatus(uId: String): DbEventState {
        return try{
            if(!isChatIdActive(uId)) {
                val ref = firebaseFireStore.collection("activeUsersData").document()
                val refId = ref.id
                val activeUsersData = ActiveUsersData(uId, "", refId)
                ref.set(activeUsersData).addOnSuccessListener {
                    Log.d("Online-Status:", "Added successfully ${System.currentTimeMillis()}")
                }.addOnFailureListener {
                    Log.d("Online-Status:", "Added failed")
                }
                ref.set(activeUsersData).await()
                Log.d("LogData", "Added successfully")
                DbEventState.Success("Active Status Added Successfully")
            } else {
                Log.d("LogData", "Already Added")
                DbEventState.Success("Already Added")
            }
        }catch (e : Exception){
            Log.d("LogData","Added failed")
            DbEventState.Error("Something went wrong")
        }

    }

    override suspend fun removeActiveStatus(uId: String): DbEventState {
        Log.d("LogData","removeActiveStatus-called")
        return try{
            firebaseFireStore.collection("activeUsersData").whereEqualTo("userId",uId).addSnapshotListener {
                snapshot, error ->
                if (error != null){
                    return@addSnapshotListener
                }
                snapshot?.documents?.forEach {
                    it.reference.delete()
                }
            }
            Log.d("LogData","Removed successfully")
            DbEventState.Success("Active Status Removed Successfully")
        }catch (e : Exception){
            Log.d("LogData","Removing failed")
            DbEventState.Error("Something went wrong")
        }
    }

    override suspend fun changeCurrentChatId(
        uId: String,
        chatId: String,
        refId: String
    ): DbEventState {
        return try{
            val activeUsersData = ActiveUsersData(uId, chatId, refId)
            firebaseFireStore.collection("activeUsersData").document(refId).set(activeUsersData).await()
            DbEventState.Success("Active Status Changed Successfully")
        }catch (e : Exception){
            DbEventState.Error("Something went wrong")
        }
    }

    override suspend fun isChatIdActive(uId: String): Boolean {
        return try {
            val userData = firebaseFireStore.collection("activeUsersData").whereEqualTo("userId",uId).get().await()
            return userData.documents.isNotEmpty()
        }
        catch (e : Exception){
            false
        }
    }

    override suspend fun getProfileData(uId: String): LiveData<ProfileData?> {
        val _profileData = MutableLiveData<ProfileData?>()
        try {
            firebaseFireStore.collection("profileData").whereEqualTo("userId",uId)
                .addSnapshotListener{
                    snapshot,error->
                    if (error != null){
                        return@addSnapshotListener
                    }
                    if (snapshot != null){
                        val profileData = snapshot.documents.mapNotNull { it.toObject(ProfileData::class.java) }
                        Log.d("getProfileData:-","${profileData[0]}")
                        _profileData.value = profileData[0]
                    }
                }
        }catch (e: Exception){
            _profileData.value = null
        }
        Log.d("getProfileData:","${_profileData.value}")
        return _profileData
    }
}