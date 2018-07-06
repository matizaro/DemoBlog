package com.example.matiz.demoblog.activity.util

interface BaseView<out T : BasePresenter<*>> {
    val presenter: T
}