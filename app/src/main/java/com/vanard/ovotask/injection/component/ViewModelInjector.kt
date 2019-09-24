package com.vanard.ovotask.injection.component

import com.vanard.ovotask.injection.module.NetworkModule
import com.vanard.ovotask.ui.activity.detail.DetailViewModel
import com.vanard.ovotask.ui.fragment.item.MovieViewModel
import com.vanard.ovotask.ui.fragment.popular.PopularListViewModel
import com.vanard.ovotask.ui.fragment.toprated.TopRatedListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(popularListViewModel: PopularListViewModel)

    fun inject(movieViewModel: MovieViewModel)

    fun inject(topRatedViewModel: TopRatedListViewModel)

    fun inject(detailViewModel: DetailViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}