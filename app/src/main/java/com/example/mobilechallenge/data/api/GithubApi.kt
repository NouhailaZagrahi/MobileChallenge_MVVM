package com.example.mobilechallenge.data.api

import com.example.mobilechallenge.data.model.GithubResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("search/repositories")
    suspend fun getTrendingRepos(
        @Query("q") query: String,
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc",
        @Query("page") page: Int = 1
    ): GithubResponseDto
}
