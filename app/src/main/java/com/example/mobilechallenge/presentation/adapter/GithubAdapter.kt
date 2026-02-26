package com.example.mobilechallenge.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mobilechallenge.databinding.ItemRepoBinding
import com.example.mobilechallenge.domain.model.GithubRepo

class GithubAdapter(private val repos: MutableList<GithubRepo>) :
    RecyclerView.Adapter<GithubAdapter.RepoViewHolder>() {

    inner class RepoViewHolder(val binding: ItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = repos[position]
        holder.binding.apply {
            tvRepoName.text = repo.name
            tvDescription.text = repo.description
            tvStars.text = repo.stars.toString()
            tvOwnerName.text = repo.ownerName
            ivAvatar.load(repo.ownerAvatar)
        }
    }

    override fun getItemCount(): Int = repos.size

    fun addItems(newRepos: List<GithubRepo>) {
        val start = repos.size
        repos.addAll(newRepos)
        notifyItemRangeInserted(start, newRepos.size)
    }
}
