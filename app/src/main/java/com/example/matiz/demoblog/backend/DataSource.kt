package com.example.matiz.demoblog.backend

import com.example.matiz.demoblog.backend.model.Comment
import com.example.matiz.demoblog.backend.model.Post
import com.example.matiz.demoblog.backend.model.User
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DataSource {
    @GET("/posts")
    fun getPosts() : Single<List<Post>>

    @GET("/comments")
    fun getPostComments(@Query("postId") postId : Int) : Single<List<Comment>>

    @GET("/users")
    fun getUser(@Query("id") id : Int) : Single<List<User>>
}