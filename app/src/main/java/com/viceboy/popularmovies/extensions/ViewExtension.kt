package com.viceboy.popularmovies.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.viceboy.popularmovies.R

fun ImageView.loadImage(url: String?) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_baseline_movie_24)
        .into(this)
}