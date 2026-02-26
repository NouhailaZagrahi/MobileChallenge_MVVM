package com.example.mobilechallenge.presentation.state

import com.example.mobilechallenge.domain.model.GithubRepo

sealed class RepoUiState {
    object Loading : RepoUiState()
    data class Success(val repos: List<GithubRepo>) : RepoUiState()
    data class Error(val message: String) : RepoUiState()
}
