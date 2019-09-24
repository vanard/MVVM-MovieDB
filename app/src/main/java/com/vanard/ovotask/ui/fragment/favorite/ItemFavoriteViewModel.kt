package com.vanard.ovotask.ui.fragment.favorite

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.vanard.ovotask.base.BaseViewModel
import com.vanard.ovotask.data.database.db
import com.vanard.ovotask.data.model.Favorite
import com.vanard.ovotask.network.getPosterPath
import com.vanard.ovotask.ui.activity.detail.DetailActivity
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity

class ItemFavoriteViewModel(context: Context) : BaseViewModel() {
    private val movieThumbnail = MutableLiveData<String>()
    private val movieTitle = MutableLiveData<String>()
    val movieFavorite = MutableLiveData<Boolean>()
    private val movie = MutableLiveData<Favorite>()
    private val favList = mutableListOf<Favorite>()

    private val mCtx = context

    fun bind(movies: Favorite){
        movieThumbnail.value = getPosterPath(movies.poster_path)
        movieTitle.value = movies.title
        movieFavorite.value = favList.contains(movies)
        movie.value = movies

        favoriteState(mCtx)
    }

    fun getImageThumbnail(): MutableLiveData<String> {
        return movieThumbnail
    }

    fun getMovieTitle(): MutableLiveData<String> {
        return movieTitle
    }

    fun onClickItem(view: View) {
        view.context.startActivity<DetailActivity>("data" to movie.value)
    }

    private fun favoriteState(mCtx: Context){
        mCtx.db.use {
            val res = select(Favorite.TABLE_FAV)
                .whereArgs(
                    "(MOVIE_ID = {id})",
                    "id" to movie.value!!.id
                )
            val fav = res.parseList(classParser<Favorite>())
            movieFavorite.value = fav.isNotEmpty()
        }
    }

    fun onClickFavorite(view: View) {
        if (movieFavorite.value!!) removeFromFav(view.context) else addToFav(view.context)
        movieFavorite.value = !movieFavorite.value!!

    }

    private fun addToFav(context: Context) {

        context.db.use{
            insert(
                Favorite.TABLE_FAV,
                Favorite.MOVIE_ID to movie.value!!.id,
                Favorite.MOVIE_OVERVIEW to movie.value!!.overview,
                Favorite.MOVIE_ORI_LANGUAGE to movie.value!!.original_language,
                Favorite.MOVIE_TITLE to movie.value!!.title,
                Favorite.MOVIE_POSTER to movie.value!!.poster_path,
                Favorite.MOVIE_BG to movie.value!!.backdrop_path,
                Favorite.MOVIE_RELEASE to movie.value!!.release_date,
                Favorite.MOVIE_POPULAR to movie.value!!.popularity,
                Favorite.MOVIE_VOTE_AVG to movie.value!!.vote_average,
                Favorite.MOVIE_VOTE_COUNT to movie.value!!.vote_count)
        }
    }

    private fun removeFromFav(context: Context) {
        context.db.use{
            delete(
                Favorite.TABLE_FAV, "(MOVIE_ID = {id})",
                "id" to movie.value!!.id)
        }
    }

}