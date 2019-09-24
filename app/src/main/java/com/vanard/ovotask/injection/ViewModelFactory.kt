package com.vanard.ovotask.injection

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.vanard.ovotask.data.database.AppDatabase
import com.vanard.ovotask.ui.fragment.popular.PopularListViewModel
import com.vanard.ovotask.ui.fragment.toprated.TopRatedListViewModel

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PopularListViewModel::class.java)) {
            val dbMovie = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "movies").build()
            @Suppress("UNCHECKED_CAST")
            return PopularListViewModel(dbMovie.movieDao()) as T
        }
        if (modelClass.isAssignableFrom(TopRatedListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "top_rated_movies").build()
            @Suppress("UNCHECKED_CAST")
            return TopRatedListViewModel(db.movieDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}