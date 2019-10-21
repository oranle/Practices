package com.oranle.practices.data

data class News(
    val id: Int,
    val title: String,
    val content: String = title
)