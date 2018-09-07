package com.example.mvp.demo.home

import com.example.mvp.demo.base.BasePresenter
import com.example.mvp.demo.common.observer.ResultObserver

/**
 * datetime：18-9-7
 * author：yilz
 */
class HomePresenter : BasePresenter<HomeMode>() {
    inline fun <reified T> initData() {
        bindLifecycleSetMainThread(
                mMode.getData<T>())
                .subscribe(object : ResultObserver<T>() {

                    override fun onNext(t: T) {
                    }
                })
    }
}