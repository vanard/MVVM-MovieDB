package com.vanard.ovotask.base

import androidx.lifecycle.ViewModel
import com.vanard.ovotask.injection.component.DaggerViewModelInjector
import com.vanard.ovotask.injection.component.ViewModelInjector
import com.vanard.ovotask.injection.module.NetworkModule
import com.vanard.ovotask.ui.fragment.item.MovieViewModel
import com.vanard.ovotask.ui.fragment.popular.PopularListViewModel
import com.vanard.ovotask.ui.fragment.toprated.TopRatedViewModel
import com.vanard.ovotask.ui.main.DetailViewModel
import com.vanard.ovotask.ui.main.PageViewModel

abstract class BaseViewModel : ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is PageViewModel -> injector.inject(this)
            is PopularListViewModel -> injector.inject(this)
            is MovieViewModel -> injector.inject(this)
            is TopRatedViewModel -> injector.inject(this)
            is DetailViewModel -> injector.inject(this)
        }
    }
}