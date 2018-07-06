package com.example.matiz.demoblog.activity.util

interface BasePresenter<T> {
    fun stop()
    var view: T
}