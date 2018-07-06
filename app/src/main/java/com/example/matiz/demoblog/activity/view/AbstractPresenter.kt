package com.example.matiz.demoblog.activity.view

import android.support.annotation.CallSuper
import com.example.matiz.demoblog.activity.util.BasePresenter
import com.example.matiz.demoblog.activity.util.BaseView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class AbstractPresenter<V : BaseView<P>, out P : BasePresenter<V>> : BasePresenter<V> {

    override lateinit var view: V

    val disposables = CompositeDisposable()

    fun launch(job: () -> Disposable) {
        disposables.add(job())
    }

    @CallSuper
    override fun stop() {
        disposables.clear()
    }
}