package com.viceboy.popularmovies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.viceboy.popularmovies.ui.movies.MoviesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val fm = supportFragmentManager
            fm.beginTransaction()
                .add(R.id.container, MoviesFragment(), MoviesFragment.TAG)
                .commit()
        }
    }
}