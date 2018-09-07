package com.example.mvp.demo.common.retrofit

import android.util.ArrayMap
import com.example.mvp.demo.common.api.ApiService
import com.example.mvp.demo.common.Constant
import com.example.mvp.demo.common.http.OkHttpInstance
import com.google.gson.JsonElement
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * datetime：18-9-6
 * author：yilz
 */
object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(Constant.getBaseUrl())
                .client(OkHttpInstance.okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private val apiService by lazy { retrofit.create(ApiService::class.java) }

    fun get(url: String, parameMap: Map<String, String>?): Observable<JsonElement> {
        var map = ArrayMap<String, String>(parameMap?.size ?: 0+Constant.phoneMap.size+1)
        if (parameMap != null) {
            map.putAll(parameMap)
        }
        map.putAll(Constant.phoneMap)
        return apiService.get(url, map, Constant.headMap)
    }

    fun post(url: String, parameMap: Map<String, String>?): Observable<JsonElement> {
        var map = ArrayMap<String, String>(parameMap?.size ?: 0+Constant.phoneMap.size+1)
        if (parameMap != null) {
            map.putAll(parameMap)
        }
        map.putAll(Constant.phoneMap)
        return apiService.post(url, map, Constant.headMap)
    }

    fun delete(url: String, parameMap: Map<String, String>?): Observable<JsonElement> {
        var map = ArrayMap<String, String>(parameMap?.size ?: 0+Constant.phoneMap.size+1)
        if (parameMap != null) {
            map.putAll(parameMap)
        }
        return apiService.delete(url, map, Constant.headMap)
    }

    fun put(url: String, parameMap: Map<String, String>?): Observable<JsonElement> {
        var map = ArrayMap<String, String>(parameMap?.size ?: 0+Constant.phoneMap.size+1)
        if (parameMap != null) {
            map.putAll(parameMap)
        }
        return apiService.put(url, map, Constant.headMap)
    }
}