package com.example.mobilechallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.mobilechallenge.data.paging.GithubPagingSource
import com.example.mobilechallenge.domain.usecase.GetTrendingReposUseCase
import com.example.mobilechallenge.presentation.state.RepoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: GetTrendingReposUseCase
) : ViewModel() {

    val pagingFlow = Pager(
        config = PagingConfig(
            pageSize = 30,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            GithubPagingSource(useCase)
        }
    ).flow.cachedIn(viewModelScope)
}


