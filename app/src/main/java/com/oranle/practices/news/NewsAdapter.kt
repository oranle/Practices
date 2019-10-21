package com.oranle.practices.news

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oranle.practices.R
import com.oranle.practices.data.News
import com.oranle.practices.databinding.ItemLayoutNewsBinding

class NewsAdapter(val context: Context, lists: List<News>) : ListAdapter<News, NewsAdapter.ViewHolder>(DiffCallbak()) {

    lateinit var viewModel: NewsItemViewModel

    init {
        submitList(lists)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        return ViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.item_layout_news, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        viewModel = NewsItemViewModel(item)
        holder.bind(viewModel)
    }

    class ViewHolder constructor(val binding: ItemLayoutNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: NewsItemViewModel) {
            binding.vm = viewModel
            binding.executePendingBindings()
        }
    }
}

class DiffCallbak : DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem == newItem
    }

}