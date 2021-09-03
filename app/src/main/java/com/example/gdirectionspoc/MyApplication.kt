package com.example.gdirectionspoc

import android.app.Application
import com.example.gdirectionspoc.di.ApplicationModule
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    private lateinit var applicationModule: ApplicationModule

    override fun onCreate() {
        super.onCreate()
        applicationModule = ApplicationModule()
    }

}