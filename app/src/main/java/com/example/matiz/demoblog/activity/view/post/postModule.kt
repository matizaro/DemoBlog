package com.example.matiz.demoblog.activity.view.post

import com.example.matiz.demoblog.activity.view.post.Params.POST_VIEW
import com.example.matiz.demoblog.activity.view.postsList.Params
import com.example.matiz.demoblog.activity.view.postsList.PostsListPresenter
import com.example.matiz.demoblog.backend.Repository
import com.example.matiz.demoblog.backend.RemoteRepository
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.dsl.module.applicationContext

val postModule = applicationContext{
    bean {RemoteRepository(get()) as Repository}
    factory {params-> PostPresenter(get(), params[POST_VIEW]) as PostContract.Presenter}
}
object Params {
    const val POST_VIEW = "POST_VIEW"
}