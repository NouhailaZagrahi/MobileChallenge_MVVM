package com.example.mobilechallenge.data.repository

import com.example.mobilechallenge.data.api.GithubApi
import com.example.mobilechallenge.domain.model.GithubRepo
import com.example.mobilechallenge.domain.repository.GithubRepository
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val api: GithubApi
) : GithubRepository {

    override suspend fun getTrendingRepos(lastDays: Int, page: Int): List<GithubRepo> {
        val date = java.time.LocalDate.now().minusDays(lastDays.toLong())
        val query = "created:>$date"
        val response = api.getTrendingRepos(query = query, page = page)
        return response.items.map { dto ->
            GithubRepo(
                name = dto.name,
                description = dto.description ?: "",
                stars = dto.stargazers_count,
                ownerName = dto.owner.login,
                ownerAvatar = dto.owner.avatar_url
            )
        }
    }
}
