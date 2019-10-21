package com.oranle.practices.news

import android.content.Context
import com.oranle.practices.base.BaseViewModel
import com.oranle.practices.data.News

class NewsItemViewModel(val news: News) : BaseViewModel() {

    val title = news.title
    val content = news.content
    val id = "${news.id}"

    override fun start(context: Context) {
    }
}