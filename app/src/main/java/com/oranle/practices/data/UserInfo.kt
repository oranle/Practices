package com.oranle.practices.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserInfo @JvmOverloads constructor(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "userName") val userName: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "headUrl") val headUrl: String,
    @ColumnInfo(name = "phoneNum") val phoneNum: String,
    @ColumnInfo(name = "remember") val remember: Boolean = false
)
