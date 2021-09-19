package com.dev.vikas.pagingsample.repository


import android.net.Uri
import androidx.paging.PagingSource
import com.dev.vikas.pagingsample.model.Data
import com.dev.vikas.pagingsample.model.KooDataModel
import com.dev.vikas.pagingsample.networking.ApiClient
import com.dev.vikas.pagingsample.networking.ApiService
import retrofit2.HttpException
import java.io.IOException

class PostsDataSource :
    PagingSource<String, Data>() {
    private val apiService = ApiClient.getClient().create(ApiService::class.java)
    override suspend fun load(params: LoadParams<String>): PagingSource.LoadResult<String, Data> {
        return try {
            val response = apiService.fetchPosts(page = params.key)
            val listing = response.body() as KooDataModel
            val uri = Uri.parse(listing?.meta?.pagination?.links?.next)
            val nextPage: String? = uri?.getQueryParameter("page")

            LoadResult.Page(
                listing?.data,
                null,
                nextPage
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override val keyReuseSupported: Boolean = true

}