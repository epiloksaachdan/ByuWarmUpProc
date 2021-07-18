package com.example.postapp.database

import androidx.room.*


@Dao
interface PostDao{

    @Insert
    fun insert(post: Post): Long

    @Update
    fun update(post: Post): Int

    @Delete
    fun delete(post: Post): Int

    @Query("SELECT *FROM Post")
    fun get(): List<Post>

    @Query("SELECT *FROM Post WHERE id = :id ")
    fun getById(id : Long): Post

}