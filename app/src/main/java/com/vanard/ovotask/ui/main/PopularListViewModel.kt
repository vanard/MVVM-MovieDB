package com.vanard.ovotask.ui.main

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.vanard.ovotask.base.BaseViewModel
import com.vanard.ovotask.network.MovieDBAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PopularListViewModel : BaseViewModel() {

    @Inject
    lateinit var movieApi: MovieDBAPI

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    init{
        loadPosts()
    }

    private fun loadPosts(){
        subscription = movieApi.getPopularMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { onRetrievePostListSuccess() },
                { onRetrievePostListError() }
            )
    }

    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(){

    }

    private fun onRetrievePostListError(){

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}