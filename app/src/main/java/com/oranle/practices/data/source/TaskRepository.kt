package com.oranle.practices.data.source

import android.content.Context
import androidx.room.Room
import com.oranle.practices.data.source.local.LocalDataBase

object TaskRepository {

    var dataBase: LocalDataBase? = null

    fun get(cxt: Context): LocalDataBase {
        val db = dataBase
            ?: createDB(cxt)
        return db
    }

    private fun createDB(cxt: Context): LocalDataBase {
        val result = Room.databaseBuilder(
            cxt.applicationContext,
            LocalDataBase::class.java,
            "Tasks.db"
        ).build()

        dataBase = result

        return result
    }
}