package com.dev.vikas.pagingsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.vikas.pagingsample.adapter.LoadItemsAdapter
import com.dev.vikas.pagingsample.adapter.PostsAdapter
import com.dev.vikas.pagingsample.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val redditPostsAdapter = PostsAdapter()
    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initializeList()
        observeLiveData()
    }

    @ExperimentalCoroutinesApi
    private fun observeLiveData() {
        //observe live data emitted by view model
        lifecycleScope.launch {
            mainViewModel.flow.collectLatest { pagingData ->
                redditPostsAdapter.submitData(pagingData)
            }
        }

    }

    private fun initializeList() {
        listRv.layoutManager = LinearLayoutManager(this)
        listRv.adapter = redditPostsAdapter
        redditPostsAdapter.withLoadStateHeaderAndFooter(
            header = LoadItemsAdapter(redditPostsAdapter::retry),
            footer = LoadItemsAdapter(redditPostsAdapter::retry)
        )

    }
}