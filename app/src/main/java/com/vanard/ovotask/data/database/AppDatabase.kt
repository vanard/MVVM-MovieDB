package com.vanard.ovotask.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vanard.ovotask.data.model.movie.MovieItem
import com.vanard.ovotask.utils.typeconverter.IntegerListConverter
import com.vanard.ovotask.utils.typeconverter.StringListConverter
import com.vanard.ovotask.utils.typeconverter.VideoListConverter



@Database(entities = [(MovieItem::class)],
    version = 1, exportSchema = false)
@TypeConverters(value = [(StringListConverter::class), (IntegerListConverter::class),
     (VideoListConverter::class)])
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}