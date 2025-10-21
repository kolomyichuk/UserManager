package com.example.getuserwithretrofitmvi

import android.app.Application
import com.example.getuserwithretrofitmvi.data.worker.LogWorker
import com.example.getuserwithretrofitmvi.di.appModule
import com.example.getuserwithretrofitmvi.di.logModule
import com.example.getuserwithretrofitmvi.di.playerModule
import com.example.getuserwithretrofitmvi.di.userModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class App : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            workManagerFactory()
            modules(listOf(appModule, playerModule, userModule, logModule))
        }

        LogWorker.scheduleDailyRotation(this)
    }

}
