package com.vanard.ovotask.network

import com.vanard.ovotask.model.MovieResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface MovieDBAPI {
    @GET("/popular")
    fun getPopularMovies(): Observable<List<MovieResponse>>
    @GET("/top_rated")
    fun getTopRatedMovies(): Observable<List<MovieResponse>>
    @GET("/{movie_id}")
    fun getDetailMovie(): Single<MovieResponse>
}