package com.oranle.practices.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oranle.practices.data.Task
import com.oranle.practices.data.UserInfo

@Database(entities = [Task::class, UserInfo::class], version = 1, exportSchema = false)
abstract class LocalDataBase: RoomDatabase() {

    abstract fun taskDao(): TaskDao

    abstract fun getUserDao(): UserDao

}