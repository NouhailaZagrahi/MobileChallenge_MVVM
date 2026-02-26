package com.example.mobilechallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilechallenge.domain.usecase.GetTrendingReposUseCase
import com.example.mobilechallenge.presentation.state.RepoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTrendingReposUseCase: GetTrendingReposUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<RepoUiState>(RepoUiState.Loading)
    val uiState: StateFlow<RepoUiState> = _uiState


    private var currentPage = 1

    fun loadRepos() {
        viewModelScope.launch {
            try {
                _uiState.value = RepoUiState.Loading

                val newRepos = getTrendingReposUseCase(page = currentPage)

                val currentList =
                    (_uiState.value as? RepoUiState.Success)?.repos ?: emptyList()

                _uiState.value =
                    RepoUiState.Success(currentList + newRepos)

                currentPage++
            } catch (e: Exception) {
                _uiState.value =
                    RepoUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

}
