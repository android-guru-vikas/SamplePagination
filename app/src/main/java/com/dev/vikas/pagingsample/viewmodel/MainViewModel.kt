package com.dev.vikas.pagingsample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.dev.vikas.pagingsample.repository.PostsDataSource

class MainViewModel : ViewModel() {

    val flow = Pager(
        PagingConfig(pageSize = 1, enablePlaceholders = false)
    ) {
        PostsDataSource()
    }.flow.cachedIn(viewModelScope)
}