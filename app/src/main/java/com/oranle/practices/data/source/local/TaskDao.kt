package com.oranle.practices.data.source.local

import androidx.room.*
import com.oranle.practices.data.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task)

    @Query("SELECT * FROM tasks")
    suspend fun getTasks(): Task

    @Query("SELECT * FROM tasks WHERE entryid = :taskId ")
    suspend fun getTask(taskId: String): Int

    @Update
    suspend fun updateTask(task: Task): Int


    @Query("DELETE FROM tasks where entryid = :taskId")
    suspend fun delteTask(taskId: String)

}