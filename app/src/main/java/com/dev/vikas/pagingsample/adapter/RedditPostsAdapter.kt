package com.dev.vikas.pagingsample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dev.vikas.pagingsample.R
import com.dev.vikas.pagingsample.model.Data
import com.dev.vikas.pagingsample.utils.DiffUtilCallBack
import kotlinx.android.synthetic.main.adapter_row.view.*


class RedditPostsAdapter :
    PagingDataAdapter<Data, RedditPostsAdapter.MyViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bindPost(it) }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val commentsText by lazy { itemView.commentsTv }
        private val titleText by lazy { itemView.titleTv }

        fun bindPost(redditPost: Data) {
            with(redditPost) {
                titleText?.text = title
                commentsText?.text = body
            }
        }
    }
}