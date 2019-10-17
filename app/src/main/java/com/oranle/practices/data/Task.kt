package com.oranle.practices.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "tasks")
data class Task @JvmOverloads constructor(
    @PrimaryKey
    @ColumnInfo(name = "entryid") val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "content") val content: String = "",
    @ColumnInfo(name = "state") val isCompleted: Boolean = false
)