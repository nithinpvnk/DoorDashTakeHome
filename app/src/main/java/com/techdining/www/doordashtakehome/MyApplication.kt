package com.techdining.www.doordashtakehome

import android.app.Application
import com.facebook.stetho.Stetho

internal class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}
