package com.oranle.practices.data

import java.util.*

data class Task(val id: String = UUID.randomUUID().toString(),
                val name: String = "",
                val content: String = "",
                val isCompleted: Boolean = false)