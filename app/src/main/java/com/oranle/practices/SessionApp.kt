package com.oranle.practices

import android.app.Application
import com.oranle.practices.data.source.local.LocalDataBase
import com.oranle.practices.data.source.TaskRepository
import timber.log.Timber

class SessionApp : Application() {

    val localDataBase: LocalDataBase
        get() = TaskRepository.get(this)

    var app: Application? = null

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        app = this
    }

}