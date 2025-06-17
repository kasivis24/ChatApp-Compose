package com.mobile.chatapp.persentation.ui.screen.chat

import androidx.lifecycle.ViewModel
import com.mobile.chatapp.feature.audioplayer.AudioPlayer
import com.mobile.chatapp.feature.record.AudioRecorder
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

@HiltViewModel
class VoiceRecorderViewModel @Inject constructor(
    private val recorder: AudioRecorder,
    private val player: AudioPlayer,
    private val outputFile: File
) : ViewModel() {

    fun startRecording() = recorder.start(outputFile)
    fun stopRecording() = recorder.stop()
    fun playRecording() = player.playFile(outputFile)
    fun stopPlaying() = player.stop()
}
