package com.example.matiz.demoblog.activity.view.post

import com.example.matiz.demoblog.activity.view.AbstractPresenter
import com.example.matiz.demoblog.backend.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PostPresenter(val repository : Repository, override var view: PostContract.View)
    : AbstractPresenter<PostContract.View, PostContract.Presenter>(), PostContract.Presenter{
    override fun displayUser(userId : Int) {
        launch{
            view.displayUserProgress()
            repository.getUser(userId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({user->
                        view.displayUserSuccess()
                        view.onGetUserSuccess(user)},
                            {error->view.onGetUserFailed(error)})
        }
    }

    override fun displayComments(postId : Int) {
        launch{
            view.displayCommentsProgress()
            repository.getComments(postId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({comments->
                        view.displayCommentsSuccess()
                        view.onGetCommentsSuccess(comments)},
                            {error->view.onGetCommentsFailed(error)})
        }
    }
}