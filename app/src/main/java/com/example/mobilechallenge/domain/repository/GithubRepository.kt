package com.example.mobilechallenge.domain.repository

import com.example.mobilechallenge.domain.model.GithubRepo

interface GithubRepository {
    suspend fun getTrendingRepos(lastDays: Int = 30, page: Int = 1): List<GithubRepo>
}
