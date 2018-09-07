package com.example.mvp.demo.home

import com.example.mvp.demo.base.BaseMode
import io.reactivex.Observable

/**
 * datetime：18-9-7
 * author：yilz
 */
class HomeMode : BaseMode() {
    inline fun <reified T> getData(vararg list: String): Observable<T> {
        return internalGet("", *list)
    }
}