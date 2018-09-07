package com.example.mvp.demo.common.observer

import android.text.TextUtils
import com.example.mvp.demo.common.utils.ToastUtils
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * datetime：18-9-7
 * author：yilz
 */
 abstract class ResultObserver<T> : Observer<T> {
    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onError(e: Throwable) {
        if (e is ResultThrowable) {
            if (!TextUtils.isEmpty(e.message)) {
                ToastUtils.showToast(e.message!!)
            }
        }
    }
}