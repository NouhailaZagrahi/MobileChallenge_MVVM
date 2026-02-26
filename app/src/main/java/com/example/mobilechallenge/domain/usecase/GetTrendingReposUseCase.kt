package com.example.mobilechallenge.domain.usecase

import com.example.mobilechallenge.domain.model.GithubRepo
import com.example.mobilechallenge.domain.repository.GithubRepository
import javax.inject.Inject

class GetTrendingReposUseCase @Inject constructor(
    private val repository: GithubRepository
) {
    suspend operator fun invoke(lastDays: Int = 30, page: Int = 1): List<GithubRepo> {
        return repository.getTrendingRepos(lastDays, page)
    }
}
