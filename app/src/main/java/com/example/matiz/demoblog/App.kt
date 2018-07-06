package com.example.matiz.demoblog

import android.app.Application
import com.example.matiz.demoblog.activity.view.post.postModule
import com.example.matiz.demoblog.backend.module.dataSourceModule
import com.example.matiz.demoblog.activity.view.postsList.postsModule
import org.koin.standalone.StandAloneContext.startKoin

class App : Application() {
    override fun onCreate(){
        super.onCreate()
        startKoin(listOf(dataSourceModule, postsModule, postModule))
    }
}