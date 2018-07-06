package com.example.matiz.demoblog.backend.module

import com.example.matiz.demoblog.BuildConfig
import com.example.matiz.demoblog.backend.DataSource
import com.example.matiz.demoblog.backend.Repository
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val dataSourceModule = applicationContext {
    bean { getDataSource() }
}

fun getDataSource() : DataSource = Retrofit.Builder()
            .baseUrl(BuildConfig.REPOSITORY_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DataSource::class.java)