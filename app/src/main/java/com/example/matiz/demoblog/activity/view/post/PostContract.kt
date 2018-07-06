package com.example.matiz.demoblog.activity.view.post

import com.example.matiz.demoblog.activity.util.BasePresenter
import com.example.matiz.demoblog.activity.util.BaseView
import com.example.matiz.demoblog.backend.model.Comment
import com.example.matiz.demoblog.backend.model.User

interface PostContract {
    interface View : BaseView<Presenter> {
        fun displayUserProgress()
        fun displayCommentsProgress()
        fun displayUserSuccess()
        fun displayCommentsSuccess()
        fun onGetUserSuccess(user: User)
        fun onGetUserFailed(error : Throwable)
        fun onGetCommentsSuccess(comments : List<Comment>)
        fun onGetCommentsFailed(error : Throwable)
    }
    interface Presenter : BasePresenter<View> {
        fun displayComments(postId : Int)
        fun displayUser(userId : Int)
    }
}