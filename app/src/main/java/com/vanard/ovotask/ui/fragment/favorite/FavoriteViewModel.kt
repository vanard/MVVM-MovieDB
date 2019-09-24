package com.vanard.ovotask.ui.fragment.favorite

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.vanard.ovotask.base.BaseViewModel
import com.vanard.ovotask.data.database.db
import com.vanard.ovotask.data.model.Favorite
import com.vanard.ovotask.data.model.movie.MovieItem
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteViewModel(context: Context) : BaseViewModel() {

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val favoriteListAdapter: FavoriteListAdapter =
        FavoriteListAdapter()
    val mCtx = context
    val movie = MutableLiveData<List<MovieItem>>()

    init {
        loadFav()
    }

    private fun loadFav() {
        mCtx.db.use {
            val res = select(Favorite.TABLE_FAV)
            val fav = res.parseList(classParser<Favorite>())
            onRetrievePostListSuccess(fav)
        }
    }

    private fun onRetrievePostListSuccess(movieList: List<Favorite>){
        favoriteListAdapter.updateMovieList(movieList)
    }

}