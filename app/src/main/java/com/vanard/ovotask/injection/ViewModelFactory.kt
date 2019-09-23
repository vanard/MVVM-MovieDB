package com.vanard.ovotask.injection

//class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(PopularListViewModel::class.java)) {
//            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "movies").build()
//            @Suppress("UNCHECKED_CAST")
//            return PopularListViewModel(db.movieDao()) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//
//    }
//}