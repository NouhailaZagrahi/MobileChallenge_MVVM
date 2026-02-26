package com.example.mobilechallenge.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mobilechallenge.databinding.ItemRepoBinding
import com.example.mobilechallenge.domain.model.GithubRepo

class GithubAdapter :
    PagingDataAdapter<GithubRepo, GithubAdapter.RepoViewHolder>(DIFF_CALLBACK) {

    inner class RepoViewHolder(val binding: ItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = ItemRepoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = getItem(position) ?: return

        holder.binding.apply {
            tvRepoName.text = repo.name
            tvDescription.text = repo.description
            tvStars.text = repo.stars.toString()
            tvOwnerName.text = repo.ownerName
            ivAvatar.load(repo.ownerAvatar)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GithubRepo>() {
            override fun areItemsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean {
                return oldItem == newItem
            }
        }
    }
}
