package com.example.mvp.demo.common.http

import com.example.mvp.demo.Application
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * datetime：18-9-6
 * author：yilz
 */
object OkHttpInstance {
    val okHttpClient: OkHttpClient by lazy { initOkHttp() }

    private fun initOkHttp(): OkHttpClient {
        return OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .cache(Cache(File(Application.getApplication().externalCacheDir, "okhttpcache"), 10 * 1024 * 1024))
                .build()
    }
}

