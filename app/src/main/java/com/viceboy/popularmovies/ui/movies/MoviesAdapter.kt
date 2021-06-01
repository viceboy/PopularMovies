package com.viceboy.popularmovies.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.viceboy.popularmovies.R
import com.viceboy.popularmovies.databinding.ItemMoviesBinding
import com.viceboy.popularmovies.extensions.loadImage
import com.viceboy.popularmovies.network.api.MovieApiFactory
import com.viceboy.popularmovies.network.model.Movie

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private val listOfMovies = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder =
        MoviesViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movies,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(listOfMovies[position])
    }

    override fun getItemCount(): Int = listOfMovies.size

    fun submitItemList(movieList: List<Movie>) {
        listOfMovies.clear()
        listOfMovies.addAll(movieList)
        notifyDataSetChanged()
    }

    inner class MoviesViewHolder(private val binding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.movie = movie
            binding.tvTitle.text = "TestY"
            binding.ivPoster.loadImage(MovieApiFactory.getPosterUrl(movie.poster_path))
        }
    }
}