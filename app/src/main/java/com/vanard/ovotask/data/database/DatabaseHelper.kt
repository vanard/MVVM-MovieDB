package com.vanard.ovotask.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.vanard.ovotask.data.model.Favorite
import org.jetbrains.anko.db.*

class DatabaseHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "test.db", null, 1){

    companion object {
        private var instance: DatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context) : DatabaseHelper{
            if (instance == null){
                instance = DatabaseHelper(ctx.applicationContext)
            }
            return instance as DatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(Favorite.TABLE_FAV, true,
            Favorite.MOVIE_ID to INTEGER + PRIMARY_KEY,
            Favorite.MOVIE_OVERVIEW to TEXT,
            Favorite.MOVIE_ORI_LANGUAGE to TEXT,
            Favorite.MOVIE_TITLE to TEXT,
            Favorite.MOVIE_POSTER to TEXT,
            Favorite.MOVIE_BG to TEXT,
            Favorite.MOVIE_RELEASE to TEXT,
            Favorite.MOVIE_POPULAR to INTEGER,
            Favorite.MOVIE_VOTE_AVG to INTEGER,
            Favorite.MOVIE_VOTE_COUNT to INTEGER
            )

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Favorite.TABLE_FAV, true)
    }

}

val Context.db : DatabaseHelper
    get() = DatabaseHelper.getInstance(applicationContext)