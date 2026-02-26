package com.example.mobilechallenge.data.model

data class GithubRepoDto(
    val name: String,
    val description: String?,
    val stargazers_count: Int,
    val owner: OwnerDto
)

data class OwnerDto(
    val login: String,
    val avatar_url: String
)

data class GithubResponseDto(
    val items: List<GithubRepoDto>
)
