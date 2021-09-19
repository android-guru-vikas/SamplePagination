package com.dev.vikas.pagingsample.utils

import androidx.recyclerview.widget.DiffUtil
import com.dev.vikas.pagingsample.model.Data

class DiffUtilCallBack : DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.title == newItem.title
                && oldItem.body == newItem.body
    }

}