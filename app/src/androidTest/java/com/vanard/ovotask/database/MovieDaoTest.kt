package com.vanard.ovotask.database

import androidx.test.runner.AndroidJUnit4
import com.vanard.ovotask.data.model.movie.MovieItem
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDaoTest : DbTest() {

    @Test
    fun insertAndReadTest() {
        val movieList = ArrayList<MovieItem>()
        val movie = MovieItem("1","1","1",false,"1",
            listOf(1,2),"1","1","1",1.2,1.2,
            1,false,123)
        movieList.add(movie)

        db.movieDao().insertMovieList(movieList)
        val loadFromDB = db.movieDao().getMovie(1)
        MatcherAssert.assertThat(loadFromDB.overview, CoreMatchers.`is`("1"))
        MatcherAssert.assertThat(loadFromDB.popularity, CoreMatchers.`is`(1.2))
    }
}