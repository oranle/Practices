package com.oranle.practices

import android.app.Application
import com.oranle.practices.data.source.local.LocalDataBase
import com.oranle.practices.data.source.TaskRepository
import timber.log.Timber
import kotlin.properties.Delegates

class SessionApp : Application() {

    val localDataBase: LocalDataBase
        get() = TaskRepository.get(this)

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        instance = this
    }

    companion object {
        var instance: SessionApp by Delegates.notNull()
    }

}