package com.mobile.chatapp.persentation.ui.screen.chat

import android.app.Application
import android.content.Context
import android.media.MediaRecorder
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

class VoiceRecorderViewModel : ViewModel() {




    private var mediaRecorder: MediaRecorder? = null
    private var outputFilePath: String = ""

    private val _isRecording = mutableStateOf(false)
    val isRecording: State<Boolean> = _isRecording

    fun startRecording(context: Context) {
        Log.d("LogData","Recoreded Start")
        try {
            outputFilePath = "${context.externalCacheDir?.absolutePath}/recorded_audio.3gp"
            mediaRecorder = MediaRecorder().apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                setOutputFile(outputFilePath)
                prepare()
                start()
            }
        }catch (e : Exception){
            Log.d("LodData","$e")
        }
    }

    fun stopRecording(): String {
        Log.d("LogData","Recoreded Stop")
        mediaRecorder?.apply {
            stop()
            release()
        }
        mediaRecorder = null
        _isRecording.value = false
        return outputFilePath
    }

    fun cancelRecording() {
        mediaRecorder?.apply {
            stop()
            release()
        }
        mediaRecorder = null
        File(outputFilePath).delete()
        _isRecording.value = false
    }
}
