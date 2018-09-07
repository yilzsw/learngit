package com.example.mvp.demo.common

import android.text.TextUtils
import android.util.ArrayMap
import com.example.mvp.demo.BuildConfig

/**
 * datetime：18-9-6
 * author：yilz
 */
object Constant {
    private var BASE_URL = ""
    private val HTTPS = "https://"
    private val HTTP = "https://"
    private val API_HOST_TEST = ""
    private val API_HOST_ONLINE = "www.baidu.com"


    fun getBaseUrl(): String {
        if (TextUtils.isEmpty(BASE_URL)) {
            if (BuildConfig.TEST_URL) {
                BASE_URL = "https://api.douban.com"
            } else {
                BASE_URL = "https://api.douban.com"
            }
        }
        return BASE_URL
    }
    /*配置公共参数*/
    val phoneMap: ArrayMap<String, String> by lazy {
        var map = ArrayMap<String, String>()
        return@lazy map
    }
    /*配置HTTP头信息*/
    val headMap: ArrayMap<String, String> by lazy {
        var map = ArrayMap<String, String>()
        return@lazy map
    }

}