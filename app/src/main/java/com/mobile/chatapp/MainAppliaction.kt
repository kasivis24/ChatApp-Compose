package com.mobile.chatapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainAppliaction : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}