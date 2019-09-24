package com.vanard.ovotask.data.database

import androidx.room.*
import com.vanard.ovotask.data.model.movie.MovieItem

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieList(movies: List<MovieItem>)

    @Update
    fun updateMovie(movie: MovieItem)

    @Query("SELECT * FROM MOVIEITEM WHERE id = :id_")
    fun getMovie(id_: Int): MovieItem

    @get:Query("SELECT * FROM MOVIEITEM")
    val all: List<MovieItem>

}