package com.vanard.ovotask.ui.fragment.item

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.MutableLiveData
import com.vanard.ovotask.R
import com.vanard.ovotask.base.BaseViewModel
import com.vanard.ovotask.model.movie.ResultsItem
import com.vanard.ovotask.network.getPosterPath
import com.vanard.ovotask.ui.main.DetailActivity
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MovieViewModel : BaseViewModel() {
    private val movieThumbnail = MutableLiveData<String>()
    private val movieTitle = MutableLiveData<String>()
    private val movieGenre = MutableLiveData<List<Int?>>()
    private val movieFavorite = MutableLiveData<Boolean>()
    private val movie = MutableLiveData<ResultsItem>()

    fun bind(movies: ResultsItem){
        movieThumbnail.value = getPosterPath(movies.poster_path)
        movieTitle.value = movies.title
        movieGenre.value = movies.genre_ids
        movieFavorite.value = false
        movie.value = movies
    }

    fun getImageThumbnail():MutableLiveData<String>{
        return movieThumbnail
    }

    fun getMovieTitle():MutableLiveData<String>{
        return movieTitle
    }

    fun getGenre():MutableLiveData<List<Int?>>{
        return movieGenre
    }

    fun onClickItem(view: View) {
       view.context.startActivity<DetailActivity>("data" to movie.value)
    }

    fun onClickFavorite(view: View) {
        movieFavorite.value = movieFavorite.value == null || movieFavorite.value == false
        view.context.toast(movieFavorite.value.toString())
        val a = view as AppCompatImageView
        if (movieFavorite.value!!)
            a.setImageResource(R.drawable.ic_favorite_heart)
        else a.setImageResource(R.drawable.ic_favorite_heart_button)

    }

}