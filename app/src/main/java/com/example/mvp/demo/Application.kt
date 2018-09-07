package com.example.mvp.demo

import android.app.Application
import android.content.Context
import com.example.mvp.demo.common.utils.ImageLoader

/**
 * datetime：18-9-6
 * author：yilz
 */
class Application : Application() {
    companion object {
        private lateinit var instance: Context

        fun getApplication(): Context {
            return instance
        }

        private fun setApplication(context: Context){
            instance = context
        }

    }

    override fun onCreate() {
        super.onCreate()
        setApplication(applicationContext)
        ImageLoader.init(this)
    }
}