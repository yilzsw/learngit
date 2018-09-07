package com.example.mvp.demo.base

import android.util.ArrayMap
import com.example.mvp.demo.bean.Result
import com.example.mvp.demo.common.observer.ResultThrowable
import com.example.mvp.demo.common.retrofit.RetrofitInstance
import com.google.gson.Gson
import com.google.gson.JsonElement
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * datetime：18-9-7
 * author：yilz
 */
abstract class BaseMode {
    protected inline fun <reified T> internalGet(url: String, vararg list: String): Observable<T> {
        var map: ArrayMap<String, String>? = convertMap(*list)
        return convertResult(RetrofitInstance.get(url, map)).map {
            if (it.code == 0) {
                return@map Gson().fromJson(it.data, T::class.java)
            } else {
                throw ResultThrowable(it.msg, it.code)
            }


        }.subscribeOn(Schedulers.io())
    }

    protected inline fun <reified T : Any> internalPost(url: String, vararg list: String): Observable<T> {
        var map: ArrayMap<String, String>? = convertMap(*list)
        return convertResult(RetrofitInstance.post(url, map)).map {
            if (it.code == 0) {
                return@map Gson().fromJson(it.data, T::class.java)
            } else {
                throw ResultThrowable(it.msg, it.code)
            }


        }.subscribeOn(Schedulers.io())
    }

    protected inline fun <reified T : Any> internalDelete(url: String, vararg list: String): Observable<T> {
        var map: ArrayMap<String, String>? = convertMap(*list)
        return convertResult(RetrofitInstance.delete(url, map)).map {
            if (it.code == 0) {
                return@map Gson().fromJson(it.data, T::class.java)
            } else {
                throw ResultThrowable(it.msg, it.code)
            }


        }.subscribeOn(Schedulers.io())
    }

    protected inline fun <reified T : Any> internalPut(url: String, vararg list: String): Observable<T> {
        var map: ArrayMap<String, String>? = convertMap(*list)
        return convertResult(RetrofitInstance.put(url, map)).map {
            if (it.code == 0) {
                return@map Gson().fromJson(it.data, T::class.java)
            } else {
                throw ResultThrowable(it.msg, it.code)
            }


        }.subscribeOn(Schedulers.io())
    }

    protected fun convertResult(observable: Observable<JsonElement>): Observable<Result> {
        return observable.map { Gson().fromJson(it, Result::class.javaObjectType) }
    }

    protected fun convertMap(vararg list: String): ArrayMap<String, String>? {
        var map: ArrayMap<String, String>?
        if (list.isNotEmpty()) {
            map = ArrayMap(list.size / 2)
            for (i in 0 until list.size step 2) {
                map[list[i]] = list[i + 1]
            }

        } else {
            map = null
        }
        return map
    }
}