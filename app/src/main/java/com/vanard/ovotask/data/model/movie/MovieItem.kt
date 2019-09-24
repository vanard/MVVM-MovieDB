package com.vanard.ovotask.data.model.movie

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(primaryKeys = [("id")])
data class MovieItem(
	val overview: String? = null,
	val original_language: String? = null,
	val original_title: String? = null,
	val video: Boolean? = null,
	val title: String? = null,
	val genre_ids: List<Int>? = null,
	val poster_path: String? = null,
	val backdrop_path: String? = null,
	val release_date: String? = null,
	val popularity: Double? = null,
	val vote_average: Double? = null,
	val id: Int,
	val adult: Boolean? = null,
	val vote_count: Int? = null
): Parcelable {
	companion object {
		const val TABLE_FAV_ : String = "TABLE_FAVORITE_"
		const val ID: String = "ID"

		const val MOVIE_ID: String = "MOVIE_ID"
		const val MOVIE_OVERVIEW: String = "HOME_TEAM_NAME"
		const val MOVIE_ORI_LANGUAGE: String = "AWAY_TEAM_NAME"
		const val MOVIE_ORI_TITLE: String = "HOME_SCORE"
		const val MOVIE_VID: String = "AWAY_SCORE"
		const val MOVIE_TITLE: String = "EVENT_DATE"
		const val MOVIE_I: String = "EVENT_TIME"

	}
}
