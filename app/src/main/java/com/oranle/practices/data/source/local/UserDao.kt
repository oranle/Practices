package com.oranle.practices.data.source.local

import androidx.room.*
import com.oranle.practices.data.Task
import com.oranle.practices.data.UserInfo

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userInfo: UserInfo)

    @Query("SELECT * FROM users")
    suspend fun getUsers(): List<UserInfo>


    @Query("SELECT * FROM users WHERE id = :id ")
    suspend fun getUserById(id: Int): Task?

    @Update
    suspend fun updateUser(userInfo: UserInfo): Int

    @Query("DELETE FROM users where id=:id")
    suspend fun deleteUser(id: Int): Int
}