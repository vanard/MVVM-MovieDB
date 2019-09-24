package com.vanard.ovotask.ui.fragment.toprated

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.vanard.ovotask.base.BaseViewModel
import com.vanard.ovotask.data.database.MovieDao
import com.vanard.ovotask.data.model.movie.MovieItem
import com.vanard.ovotask.data.model.movie.MovieResponse
import com.vanard.ovotask.network.MovieDBAPI
import com.vanard.ovotask.ui.fragment.item.PopularListAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TopRatedListViewModel(
    private val movieDao: MovieDao
) : BaseViewModel() {
    @Inject
    lateinit var movieApi: MovieDBAPI

    private lateinit var subscription: Disposable

    val popularListAdapter: PopularListAdapter =
        PopularListAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadMovies() }


    init{
        loadMovies()
    }

    private fun loadMovies(){
        subscription =
            Observable.fromCallable { movieDao.all }
                .concatMap {
                        dbMovieList ->
                    if(dbMovieList.isEmpty())
                        movieApi.getTopRatedMovies()
                            .concatMap {
                                    apiMovieList -> movieDao.insertMovieList(apiMovieList.results as List<MovieItem>)
                                Observable.just(apiMovieList)
                            }
                    else
                        Observable.just(dbMovieList)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish() }
                .subscribe(
                    { result ->
                        try {
                            val res = result as MovieResponse
                            onRetrievePostListSuccess(res.results)
                        }catch (e: ClassCastException){
                            val res = result as List<MovieItem>
                            onRetrievePostListSuccess(res)
                        }
                    },
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

    private fun onRetrievePostListSuccess(movieList: List<MovieItem?>?){
        popularListAdapter.updateMovieList(movieList as List<MovieItem>)
    }

    private fun onRetrievePostListError(localizedMessage: String?) {
        errorMessage.value = localizedMessage
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}