package com.oranle.practices

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.oranle.practices.data.source.TaskRepository
import com.oranle.practices.data.source.local.LocalDataBase
import com.oranle.practices.koin.appModule
import com.oranle.practices.util.DynamicTimeFormat
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.header.ClassicsHeader
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

    init {
        //启用矢量图兼容
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        //设置全局默认配置（优先级最低，会被其他设置覆盖）
        SmartRefreshLayout.setDefaultRefreshInitializer { context, layout ->
            //全局设置（优先级最低）
            layout.setEnableAutoLoadMore(true)
            layout.setEnableOverScrollDrag(false)
            layout.setEnableOverScrollBounce(true)
            layout.setEnableLoadMoreWhenContentNotFull(true)
            layout.setEnableScrollContentWhenRefreshed(true)
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)
        }
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            //全局设置主题颜色（优先级第二低，可以覆盖 DefaultRefreshInitializer 的配置，与下面的ClassicsHeader绑定）
            ClassicsHeader(context).setTimeFormat(DynamicTimeFormat("更新于 %s"))
        }
    }

}