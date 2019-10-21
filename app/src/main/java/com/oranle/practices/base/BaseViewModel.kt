package com.oranle.practices.base

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import com.oranle.practices.SessionApp
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

abstract class BaseViewModel: ViewModel(), ClickListener {

    val UI = Dispatchers.Main
    val IO = Dispatchers.IO

    abstract fun start(context: Context)

    fun getDB(context: Context) = ((context.applicationContext) as SessionApp).localDataBase

    override fun onClick(view: View) {
        Timber.v("on click id ${view.id}")
    }

}

interface ClickListener {

    fun onClick(view: View)

}