package com.oranle.practices.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oranle.practices.data.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class LocalDataBase: RoomDatabase() {

    abstract fun taskDao(): TaskDao

}