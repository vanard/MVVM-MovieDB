package com.vanard.ovotask.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Favorite(
    val id: Int,
    val overview: String? = null,
    val original_language: String? = null,
    val title: String? = null,
    val poster_path: String? = null,
    val backdrop_path: String? = null,
    val release_date: String? = null,
    val popularity: Double? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null
): Parcelable {
    companion object {
        const val TABLE_FAV : String = "TABLE_FAVORITE"

        const val MOVIE_ID: String = "MOVIE_ID"
        const val MOVIE_OVERVIEW: String = "MOVIE_OVERVIEW"
        const val MOVIE_ORI_LANGUAGE: String = "MOVIE_ORI_LANGUAGE"
        const val MOVIE_TITLE: String = "MOVIE_TITLE"
        const val MOVIE_POSTER: String = "MOVIE_POSTER"
        const val MOVIE_BG: String = "MOVIE_BG"
        const val MOVIE_RELEASE: String = "MOVIE_RELEASE"
        const val MOVIE_POPULAR: String = "MOVIE_POPULAR"
        const val MOVIE_VOTE_AVG: String = "MOVIE_VOTE_AVG"
        const val MOVIE_VOTE_COUNT: String = "MOVIE_VOTE_COUNT"

    }
}
