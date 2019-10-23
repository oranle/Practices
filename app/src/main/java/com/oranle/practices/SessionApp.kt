package com.oranle.practices

import android.app.Application
import com.oranle.practices.data.source.local.LocalDataBase
import com.oranle.practices.data.source.TaskRepository
import com.oranle.practices.koin.appModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber
import kotlin.properties.Delegates

class SessionApp : Application() {

    val localDataBase: LocalDataBase
        get() = TaskRepository.get(this)

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        instance = this

        startKoin(this, listOf(appModule))
    }

    companion object {
        var instance: SessionApp by Delegates.notNull()
    }

}