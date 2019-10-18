package com.oranle.practices.data.source.local

import androidx.room.*
import com.oranle.practices.data.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task)

    @Query("SELECT * FROM tasks")
    suspend fun getTasks(): List<Task>

    @Query("SELECT * FROM tasks WHERE taskId = :taskId ")
    suspend fun getTask(taskId: String): Task?

    @Update
    suspend fun updateTask(task: Task): Int


    @Query("DELETE FROM tasks where taskId = :taskId")
    suspend fun deleteTask(taskId: String): Int

}