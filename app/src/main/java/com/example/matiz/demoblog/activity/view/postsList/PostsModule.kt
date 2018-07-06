package com.example.matiz.demoblog.activity.view.postsList

import com.example.matiz.demoblog.activity.view.postsList.Params.POSTS_VIEW
import com.example.matiz.demoblog.backend.Repository
import com.example.matiz.demoblog.backend.RemoteRepository
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.dsl.module.applicationContext

val postsModule = applicationContext{
    bean {RemoteRepository(get()) as Repository}
    factory {params-> PostsListPresenter(get(), params[POSTS_VIEW]) as PostsListContract.Presenter}
}
object Params {
    const val POSTS_VIEW = "POSTS_VIEW"
}