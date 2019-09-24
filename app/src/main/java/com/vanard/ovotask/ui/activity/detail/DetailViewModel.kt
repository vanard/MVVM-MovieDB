package com.vanard.ovotask.ui.activity.detail

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.vanard.ovotask.base.BaseViewModel
import com.vanard.ovotask.data.database.db
import com.vanard.ovotask.data.model.Favorite
import com.vanard.ovotask.data.model.movie.MovieItem
import com.vanard.ovotask.data.model.video.VideoListResponse
import com.vanard.ovotask.network.MovieDBAPI
import com.vanard.ovotask.network.getBackdropPath
import com.vanard.ovotask.network.getPosterPath
import com.vanard.ovotask.utils.FormatDate.DATE_FORMAT
import com.vanard.ovotask.utils.FormatDate.DATE_FORMAT_OUTPUT
import com.vanard.ovotask.utils.FormatDate.reformatStringDate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import javax.inject.Inject

class DetailViewModel : BaseViewModel() {
    @Inject
    lateinit var movieApi: MovieDBAPI

    private lateinit var subscription: Disposable

    val movie = MutableLiveData<MovieItem>()
    val videoListAdapter: VideoListAdapter = VideoListAdapter()

    private val movieBgThumbnail = MutableLiveData<String>()
    private val movieThumbnail = MutableLiveData<String>()
    private val movieTitle = MutableLiveData<String>()
    private val movieId = MutableLiveData<Int>()
    private val voteAvg = MutableLiveData<String>()
    private val voteCount = MutableLiveData<String>()
    private val relDate = MutableLiveData<String>()
    private val overView = MutableLiveData<String>()
    private val lang = MutableLiveData<String>()
    val movieFavorite = MutableLiveData<Boolean>()
    val itemVisibility: MutableLiveData<Int> = MutableLiveData()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<String> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadVideos() }

    fun initData() {
        if (movie.value != null){
            movieBgThumbnail.value = getBackdropPath(movie.value!!.backdrop_path)
            movieThumbnail.value = getPosterPath(movie.value!!.poster_path)
            movieTitle.value = movie.value!!.title
            voteAvg.value = "${movie.value!!.vote_average}/10"
            voteCount.value = "${movie.value!!.vote_count} votes"
            relDate.value = reformatStringDate(movie.value!!.release_date!!, DATE_FORMAT, DATE_FORMAT_OUTPUT)
            overView.value = movie.value!!.overview
            lang.value = movie.value!!.original_language
            movieId.value = movie.value!!.id

            loadVideos()
        }

    }

    private fun loadVideos() {
        subscription = movieApi.fetchVideos(movie.value!!.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish() }
                .subscribe(
                    { result -> onRetrievePostListSuccess(result) },
                    { error -> onRetrievePostListError(error.localizedMessage) }
                )
    }

    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(videoList: VideoListResponse){
        videoListAdapter.updateVideoList(videoList.results)
        if (videoList.results.isEmpty())
            itemVisibility.value = View.INVISIBLE
        else itemVisibility.value = View.VISIBLE
    }

    private fun onRetrievePostListError(localizedMessage: String?) {
        errorMessage.value = localizedMessage
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

    fun getVoteAvg():MutableLiveData<String>{
        return voteAvg
    }

    fun getVoteCount():MutableLiveData<String>{
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

    fun onClickFavorite(view: View) {
        if (movieFavorite.value!!) removeFromFav(view.context) else addToFav(view.context)
        movieFavorite.value = !movieFavorite.value!!
    }

    private fun addToFav(context: Context?) {
        context!!.db.use{
            insert(Favorite.TABLE_FAV,
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

    private fun removeFromFav(context: Context?) {
        context!!.db.use{
            delete(Favorite.TABLE_FAV, "(MOVIE_ID = {id})",
                "id" to movieId.value as Int)
        }
    }

    fun favoriteState(context: Context){
        context.db.use {
            val res = select(Favorite.TABLE_FAV)
                .whereArgs(
                    "(MOVIE_ID = {id})",
                    "id" to movieId.value as Int
                )

            val fav = res.parseList(classParser<Favorite>())
            movieFavorite.value = fav.isNotEmpty()
        }
    }

}