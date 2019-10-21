package com.oranle.practices.news

import android.content.Context
import androidx.databinding.ObservableArrayList
import com.oranle.practices.base.BaseViewModel
import com.oranle.practices.data.News

class NewsViewModel : BaseViewModel() {

    val news: ArrayList<News> = ObservableArrayList()

    override fun start(context: Context) {

        loadNews()

    }

    private fun loadNews() {

        for (i in 1..10) {
            val new = News(i, "title$i", "content $i")
            news.add(new)
        }



    }

}