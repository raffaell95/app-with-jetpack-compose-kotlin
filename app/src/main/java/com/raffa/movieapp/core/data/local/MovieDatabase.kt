package com.raffa.movieapp.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raffa.movieapp.core.data.local.dao.MovieDao
import com.raffa.movieapp.core.data.local.entity.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}