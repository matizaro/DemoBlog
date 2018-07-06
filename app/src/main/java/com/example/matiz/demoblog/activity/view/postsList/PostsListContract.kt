package com.example.matiz.demoblog.activity.view.postsList

import com.example.matiz.demoblog.activity.util.BasePresenter
import com.example.matiz.demoblog.activity.util.BaseView
import com.example.matiz.demoblog.backend.model.Post

interface PostsListContract {
    interface View : BaseView<Presenter> {
        fun displayProgress()
        fun displaySuccess()
        fun onGetPostsSuccess(posts : List<Post>)
        fun onGetPostsFailed(error : Throwable)
    }
    interface Presenter : BasePresenter<View> {
        fun displayPosts()
    }
}