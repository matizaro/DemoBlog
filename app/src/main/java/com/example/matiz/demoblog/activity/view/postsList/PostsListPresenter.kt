package com.example.matiz.demoblog.activity.view.postsList

import com.example.matiz.demoblog.activity.view.AbstractPresenter
import com.example.matiz.demoblog.backend.DataSource
import com.example.matiz.demoblog.backend.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PostsListPresenter(val repository : Repository, override var view: PostsListContract.View)
    : AbstractPresenter<PostsListContract.View, PostsListContract.Presenter>(), PostsListContract.Presenter{
    override fun displayPosts() {
        view.displayProgress()
        launch{
            repository
                    .getPosts()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({posts-> view.displaySuccess()
                        view.onGetPostsSuccess(posts) },
                            {error-> view.onGetPostsFailed(error) })

        }
    }
}