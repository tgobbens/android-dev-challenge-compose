package com.example.androiddevchallenge

import android.app.Application
import com.example.androiddevchallenge.di.AppContainer
import com.example.androiddevchallenge.di.AppContainerImpl

class App : Application() {

    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()

        appContainer = AppContainerImpl()
    }
}