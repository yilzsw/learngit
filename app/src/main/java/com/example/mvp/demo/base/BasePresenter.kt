package com.example.mvp.demo.base

import android.app.Activity
import android.support.v4.app.Fragment
import com.example.mvp.demo.base.contract.Contract
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.android.FragmentEvent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * datetime：18-9-7
 * author：yilz
 */
abstract class BasePresenter<T : BaseMode> : Contract.IIPresenter {

    protected var mView: Contract.IView? = null
    protected open lateinit var mMode: T

    override fun initModeAndView(iView: Contract.IView, newMode: BaseMode) {
        mView = iView
        mMode = newMode as T

    }

    override fun destroy() {
        mView = null
    }

    protected inline fun <reified T> bindLifecycleSetMainThread(observable: Observable<T>): Observable<T> {
        if (mView == null)
            return observable
        var newObservable = observable
        if (LifecycleProvider::class.java.isAssignableFrom(mView!!::class.java)) {
            if (mView is Activity) {
                newObservable = observable.compose((mView as LifecycleProvider<ActivityEvent>).bindToLifecycle())
            } else if (mView is Fragment) {
                newObservable = observable.compose((mView as LifecycleProvider<FragmentEvent>).bindToLifecycle())

            }
        }
        return newObservable.observeOn(AndroidSchedulers.mainThread())

    }
}