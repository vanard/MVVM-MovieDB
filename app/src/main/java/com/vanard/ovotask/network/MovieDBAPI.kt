package com.vanard.ovotask.network

import com.vanard.ovotask.data.model.movie.MovieResponse
import com.vanard.ovotask.data.model.video.VideoListResponse
import com.vanard.ovotask.utils.BASE_BACKDROP_PATH
import com.vanard.ovotask.utils.BASE_POSTER_PATH
import com.vanard.ovotask.utils.YOUTUBE_THUMBNAIL_URL
import com.vanard.ovotask.utils.YOUTUBE_VIDEO_URL
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
    @GET("movie/{movie_id}/videos")
    fun fetchVideos(@Path("movie_id") id: Int): Single<VideoListResponse>
}

fun getPosterPath(posterPath: String?): String {
    return BASE_POSTER_PATH + posterPath
}

fun getBackdropPath(backdropPath: String?): String {
    return BASE_BACKDROP_PATH + backdropPath
}

fun getYoutubeVideoPath(videoPath: String): String {
    return YOUTUBE_VIDEO_URL + videoPath
}

fun getYoutubeThumbnailPath(thumbnailPath: String): String {
    return "$YOUTUBE_THUMBNAIL_URL$thumbnailPath/default.jpg"
}