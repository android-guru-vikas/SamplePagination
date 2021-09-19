package com.dev.vikas.pagingsample.networking

import com.dev.vikas.pagingsample.model.KooDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/posts")
    suspend fun fetchPosts(
        @Query("page") page: String? = null,
    ): Response<KooDataModel>
}