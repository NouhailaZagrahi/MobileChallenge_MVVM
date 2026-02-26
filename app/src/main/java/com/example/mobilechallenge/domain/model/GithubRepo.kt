package com.example.mobilechallenge.domain.model

data class GithubRepo(
    val name: String,
    val description: String,
    val stars: Int,
    val ownerName: String,
    val ownerAvatar: String
)
