package com.vanard.ovotask.network

import com.vanard.ovotask.model.GenreResponse
import com.vanard.ovotask.model.movie.MovieResponse
import com.vanard.ovotask.model.video.VideoListResponse
import com.vanard.ovotask.utils.BASE_BACKDROP_PATH
import com.vanard.ovotask.utils.BASE_POSTER_PATH
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDBAPI {
    @GET("movie/popular")
    fun getPopularMovies(): Observable<MovieResponse>
    @GET("movie/top_rated")
    fun getTopRatedMovies(): Observable<MovieResponse>
    @GET("movie/{movie_id}")
    fun getDetailMovie(@Path("movie_id") movieId: String): Single<MovieResponse>
    @GET("genre/movie/list")
    fun getGenreList(): Single<GenreResponse>
    @GET("movie/{movie_id}/videos")
    fun fetchVideos(@Path("movie_id") id: Int): Single<VideoListResponse>
}

fun getPosterPath(posterPath: String?): String {
    return BASE_POSTER_PATH + posterPath
}

fun getBackdropPath(backdropPath: String?): String {
    return BASE_BACKDROP_PATH + backdropPath
}