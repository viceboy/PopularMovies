package com.viceboy.popularmovies.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.viceboy.popularmovies.db.AppDatabase
import com.viceboy.popularmovies.utils.TestUtils
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class MovieDaoTest {

    private lateinit var db : AppDatabase

    @Before
    fun setUpDb() {
        db = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @Test
    fun basicDaoTest() {
        val movieList = TestUtils.mockMovieList()
        db.movieDao.saveMovies(movieList)
        val data = db.movieDao.getMovies()[0]
        assertThat(data.id,`is`(TestUtils.testId))
        assertThat(data.title,`is`(TestUtils.testTitle))

        db.movieDao.deleteMovie(data)

        val list = db.movieDao.getMovies()
        assertThat(!list.contains(data),`is`(true))
    }

    @After
    fun closeDb() {
        db.close()
    }
}