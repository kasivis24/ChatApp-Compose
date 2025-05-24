package com.mobile.chatapp.data.remote.repo

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.mobile.chatapp.data.dto.DuoRequestData
import com.mobile.chatapp.data.dto.ProfileData
import com.mobile.chatapp.data.dto.RequestData
import com.mobile.chatapp.data.dto.SearchData
import com.mobile.chatapp.data.dto.UserData
import com.mobile.chatapp.data.remote.db.Database
import com.mobile.chatapp.data.remote.state.DbEventState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await

class FirebaseFireStoreRepository : Database {

    private val firebaseFireStore = FirebaseFirestore.getInstance()


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

    override suspend fun addProfileData(profileData: ProfileData): DbEventState {
        return try {
            firebaseFireStore.collection("profileData").document()
                .set(profileData).await()
            DbEventState.Success("ProfileData Added Success")
        }
        catch (e : Exception){
            DbEventState.Error("Something went wrong")
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

                                                val tempSearchedProfilesList = mutableStateListOf<SearchData>()

                                                val profiles = snapshotProfile.documents.mapNotNull { it.toObject(ProfileData::class.java) }
                                                    .filter { profile ->
                                                        profile.name.contains(searchQuery, ignoreCase = true) || profile.mail.contains(searchQuery, ignoreCase = true)
                                                    }


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

                                                if (requestDataForEach.senderId == profileDataforEach.userId){
                                                    tempProfilesList.add(DuoRequestData(requestDataForEach.requestId,profileDataforEach.name,profileDataforEach.mail,requestDataForEach.day,requestDataForEach.date,requestDataForEach.time))
                                                }
                                                Log.d("LogData","for lop $it")
                                            }
                                        }

                                        _requestProfilesFlow.value = tempProfilesList
                                        Log.d("LogData","List Dtaa ${requestProfiles.size} and ${requestProfiles}")
                                        Log.d("LogData","Result -> ${_requestProfilesFlow.value}")
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


}