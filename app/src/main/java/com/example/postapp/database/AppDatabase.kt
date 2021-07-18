package com.example.postapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Post::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase(){
    abstract fun postDao(): PostDao

    companion object{
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null){
                instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java,"Todo.db" )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance as AppDatabase
        }
    }
}