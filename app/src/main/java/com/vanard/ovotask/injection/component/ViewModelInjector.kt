package com.vanard.ovotask.injection.component

import com.vanard.ovotask.injection.module.NetworkModule
import com.vanard.ovotask.ui.main.PageViewModel
import com.vanard.ovotask.ui.main.PopularListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(pageViewModel: PageViewModel)

    fun inject(popularListViewModel: PopularListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}