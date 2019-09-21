package com.vanard.ovotask.base

import androidx.lifecycle.ViewModel
import com.vanard.ovotask.injection.component.DaggerViewModelInjector
import com.vanard.ovotask.injection.component.ViewModelInjector
import com.vanard.ovotask.injection.module.NetworkModule
import com.vanard.ovotask.ui.main.PageViewModel
import com.vanard.ovotask.ui.main.PopularListViewModel

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
        }
    }
}