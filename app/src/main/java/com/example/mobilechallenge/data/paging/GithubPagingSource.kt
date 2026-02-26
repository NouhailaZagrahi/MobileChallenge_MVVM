package com.example.mobilechallenge.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mobilechallenge.domain.model.GithubRepo
import com.example.mobilechallenge.domain.usecase.GetTrendingReposUseCase

class GithubPagingSource(
    private val useCase: GetTrendingReposUseCase
) : PagingSource<Int, GithubRepo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubRepo> {
        return try {
            val page = params.key ?: 1
            val repos = useCase(lastDays = 30, page = page)

            LoadResult.Page(
                data = repos,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (repos.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GithubRepo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}