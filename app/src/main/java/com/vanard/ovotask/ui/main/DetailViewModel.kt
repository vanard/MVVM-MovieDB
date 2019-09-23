package com.vanard.ovotask.ui.main

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.MutableLiveData
import com.vanard.ovotask.R
import com.vanard.ovotask.base.BaseViewModel
import com.vanard.ovotask.model.movie.ResultsItem
import com.vanard.ovotask.network.MovieDBAPI
import com.vanard.ovotask.network.getBackdropPath
import com.vanard.ovotask.network.getPosterPath
import com.vanard.ovotask.utils.FormatDate.DATE_FORMAT
import com.vanard.ovotask.utils.FormatDate.DATE_FORMAT_OUTPUT
import com.vanard.ovotask.utils.FormatDate.reformatStringDate
import org.jetbrains.anko.toast
import javax.inject.Inject

class DetailViewModel : BaseViewModel() {
    @Inject
    lateinit var movieApi: MovieDBAPI

    val movie = MutableLiveData<ResultsItem>()

    private val movieBgThumbnail = MutableLiveData<String>()
    private val movieThumbnail = MutableLiveData<String>()
    private val movieTitle = MutableLiveData<String>()
    private val movieGenre = MutableLiveData<List<Int?>>()
    private val voteAvg = MutableLiveData<Double>()
    private val voteCount = MutableLiveData<Int>()
    private val relDate = MutableLiveData<String>()
    private val overView = MutableLiveData<String>()
    private val lang = MutableLiveData<String>()
    private val movieFavorite = MutableLiveData<Boolean>()
    val itemVisibility: MutableLiveData<Int> = MutableLiveData()

    fun initData() {
        if (movie.value != null){
            movieBgThumbnail.value = getBackdropPath(movie.value!!.backdrop_path)
            movieThumbnail.value = getPosterPath(movie.value!!.poster_path)
            movieTitle.value = movie.value!!.title
            voteAvg.value = movie.value!!.vote_average
            voteCount.value = movie.value!!.vote_count
            relDate.value = reformatStringDate(movie.value!!.release_date!!, DATE_FORMAT, DATE_FORMAT_OUTPUT)
            overView.value = movie.value!!.overview
            lang.value = movie.value!!.original_language
        }
    }

    fun getBgThumbnail():MutableLiveData<String>{
        return movieBgThumbnail
    }

    fun getImageThumbnail():MutableLiveData<String>{
        return movieThumbnail
    }

    fun getMovieTitle():MutableLiveData<String>{
        return movieTitle
    }

    fun getVoteAvg():MutableLiveData<Double>{
        return voteAvg
    }

    fun getVoteCount():MutableLiveData<Int>{
        return voteCount
    }

    fun getRelDate():MutableLiveData<String>{
        return relDate
    }

    fun getOverview():MutableLiveData<String>{
        return overView
    }

    fun getLang():MutableLiveData<String>{
        return lang
    }

    fun getVisibility():MutableLiveData<Int>{
        return if (movie.value?.video != null) {
            itemVisibility.value = View.VISIBLE
            itemVisibility
        } else {
            itemVisibility.value = View.GONE
            itemVisibility
        }
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