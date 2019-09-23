package com.vanard.ovotask.ui.fragment.toprated

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.vanard.ovotask.base.BaseViewModel
import com.vanard.ovotask.model.movie.MovieResponse
import com.vanard.ovotask.model.movie.ResultsItem
import com.vanard.ovotask.network.MovieDBAPI
import com.vanard.ovotask.ui.fragment.popular.PopularListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TopRatedViewModel : BaseViewModel() {
    @Inject
    lateinit var movieApi: MovieDBAPI

    private lateinit var subscription: Disposable

    val popularListAdapter: PopularListAdapter = PopularListAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadMovies() }


    init{
        loadMovies()
    }

    private fun loadMovies(){
        subscription = movieApi.getTopRatedMovies()
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

    private fun onRetrievePostListSuccess(movieList: MovieResponse){
        popularListAdapter.updateMovieList(movieList.results as List<ResultsItem>)
    }

    private fun onRetrievePostListError(localizedMessage: String?) {
        errorMessage.value = localizedMessage
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}