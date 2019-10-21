package com.oranle.practices.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.oranle.practices.R
import com.oranle.practices.databinding.NewsActivityBinding

class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)

        DataBindingUtil.setContentView<NewsActivityBinding>(this, R.layout.news_activity).let {
            it.vm = newsViewModel
            it.newsRecycleView.adapter = NewsAdapter(this@NewsActivity, newsViewModel.news)
            it.lifecycleOwner = this@NewsActivity
        }
        newsViewModel.start(this)
    }

}