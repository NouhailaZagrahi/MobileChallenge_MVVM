package com.example.mobilechallenge.data.model

import com.google.gson.annotations.SerializedName

data class GithubRepoDto(
    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("stargazers_count")
    val stargazers_count: Int,

    @SerializedName("owner")
    val owner: OwnerDto
)

data class OwnerDto(
    @SerializedName("login")
    val login: String,

    @SerializedName("avatar_url")
    val avatar_url: String
)

data class GithubResponseDto(
    @SerializedName("items")
    val items: List<GithubRepoDto>
)
