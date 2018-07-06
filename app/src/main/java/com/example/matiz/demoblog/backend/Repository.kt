package com.example.matiz.demoblog.backend

import com.example.matiz.demoblog.backend.model.Comment
import com.example.matiz.demoblog.backend.model.Post
import com.example.matiz.demoblog.backend.model.User
import io.reactivex.Observable
import io.reactivex.Single

interface Repository {
    fun getPosts() : Single<List<Post>>
    fun getComments(postId : Int) : Single<List<Comment>>
    fun getUser(userId : Int) : Single<User>
}

class RemoteRepository(private val dataSource : DataSource) : Repository{
    override fun getUser(userId: Int) = dataSource.getUser(userId).map {user->
        if(user.isNotEmpty()){
            user[0]
        }else{
            throw Exception("there is no user with this id")
        }
    }
    override fun getPosts(): Single<List<Post>> = dataSource.getPosts()
    override fun getComments(postId: Int): Single<List<Comment>> = dataSource.getPostComments(postId)
}