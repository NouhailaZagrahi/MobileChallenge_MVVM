package com.example.mobilechallenge.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilechallenge.databinding.FragmentMainBinding
import com.example.mobilechallenge.presentation.adapter.GithubAdapter
import com.example.mobilechallenge.presentation.state.RepoUiState
import com.example.mobilechallenge.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: GithubAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = GithubAdapter(mutableListOf())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.uiState.collectLatest { state ->
                when (state) {

                    is RepoUiState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.errorText.visibility = View.GONE
                    }

                    is RepoUiState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.errorText.visibility = View.GONE
                        adapter.addItems(state.repos)
                    }

                    is RepoUiState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.errorText.visibility = View.VISIBLE
                        binding.errorText.text = state.message
                    }
                }
            }


        }

        viewModel.loadRepos()

        // Pagination
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                val lm = rv.layoutManager as LinearLayoutManager
                if (lm.findLastCompletelyVisibleItemPosition() == adapter.itemCount - 1) {
                    viewModel.loadRepos()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
