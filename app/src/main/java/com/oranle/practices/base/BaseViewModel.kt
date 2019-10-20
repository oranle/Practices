package com.oranle.practices.base

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import com.oranle.practices.SessionApp

abstract class BaseViewModel: ViewModel(), ClickListener {

    abstract fun start(context: Context)

    fun getDB(context: Context) = ((context.applicationContext) as SessionApp).localDataBase

    override fun onClick(view: View) {
    }

}

interface ClickListener {

    fun onClick(view: View)

}