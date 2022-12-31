package com.liam.android.moviekbz.app

import android.app.Application
import com.facebook.stetho.Stetho
import com.liam.android.moviekbz.di.operator.GlobalResponseOperator
import com.skydoves.sandwich.SandwichInitializer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieKBZ : Application() {
    override fun onCreate() {
        super.onCreate()

        SandwichInitializer.sandwichOperator = GlobalResponseOperator<Any>(this)

        Stetho.initializeWithDefaults(this)
    }
}