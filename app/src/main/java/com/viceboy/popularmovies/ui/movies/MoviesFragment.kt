package com.viceboy.popularmovies.ui.movies

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import com.viceboy.popularmovies.R
import com.viceboy.popularmovies.databinding.FragmentMoviesBinding
import com.viceboy.popularmovies.network.model.Movie
import com.viceboy.popularmovies.ui.base.BaseFragment
import com.viceboy.popularmovies.ui.state.LoadState
import com.viceboy.popularmovies.ui.state.Resource
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : BaseFragment<FragmentMoviesBinding>() {

    private val viewModel by viewModel<MoviesViewModel>()
    private val moviesAdapter by lazy { MoviesAdapter() }

    override fun layoutRes(): Int = R.layout.fragment_movies

    override fun onCreate(binding: FragmentMoviesBinding) {
        binding.rvMovies.adapter = moviesAdapter
        binding.rvMovies.addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))
    }

    override fun observeLiveData() {
        viewModel.moviesLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> Unit
                is Resource.Success -> showData(it.data)
                is Resource.Failure -> showError(it.exception)
            }
        })
    }

    private fun showError(exception: Throwable) {
        binding?.loadState = LoadState.FAILURE
        binding?.errorText = "Test message"
    }

    private fun showData(data: List<Movie>) {
        binding?.loadState = LoadState.SUCCESS
        moviesAdapter.submitItemList(data)
    }

    private fun showProgress() {
        binding?.loadState = LoadState.LOADING
    }

    override fun setUpBinding(binding: FragmentMoviesBinding?) {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    companion object {
        const val TAG = "movies_fragment"
    }
}