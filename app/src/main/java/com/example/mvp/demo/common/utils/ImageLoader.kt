package com.example.mvp.demo.common.utils

import android.content.Context
import android.graphics.Bitmap
import android.text.TextUtils
import android.widget.ImageView
import com.example.mvp.demo.common.http.OkHttpInstance
import com.squareup.picasso.Callback
import com.squareup.picasso.LruCache
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

/**
 * datetime：18-9-6
 * author：yilz
 */
object ImageLoader {

    private lateinit var picasso: Picasso
    fun init(context: Context) {
        var pb: Picasso.Builder = Picasso.Builder(context)
        var memoryCache = LruCache(context)
        picasso = pb.memoryCache(memoryCache).defaultBitmapConfig(Bitmap.Config.RGB_565).downloader(OkHttp3Downloader(OkHttpInstance.okHttpClient)).build()
        Picasso.setSingletonInstance(picasso)
        picasso.setIndicatorsEnabled(true)
    }

    fun setImage(tag: Context, imageView: ImageView, url: String) {
        setImage(tag, imageView, url, 0)
    }

    fun setImage(tag: Context, imageView: ImageView, url: String, defaultID: Int) {
        setImage(tag, imageView, url, 0, 0)
    }

    fun setImage(tag: Context, imageView: ImageView, url: String, defaultID: Int, errorID: Int) {
        setImage(tag, imageView, url, 0, 0, null)
    }

    fun setImage(tag: Context, imageView: ImageView, url: String, defaultID: Int, errorID: Int, callBack: Callback?) {
        if (imageView == null || imageView == null || TextUtils.isEmpty(url)) {
            return
        }
        var rq = picasso.load(url)
        try {
            rq.tag(tag)
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
        if (defaultID > 0) {
            rq.placeholder(defaultID)
        }
        if (errorID > 0) {
            rq.error(errorID)
        }
        when (imageView.scaleType) {
            ImageView.ScaleType.FIT_XY -> rq.fit()
            ImageView.ScaleType.CENTER_CROP -> {
                rq.fit()
                rq.centerCrop()
            }
            ImageView.ScaleType.CENTER_INSIDE -> {
                rq.fit()
                rq.centerInside()
            }
            else -> rq.fit()
        }
        rq.into(imageView, callBack)
    }

    fun resumeTag(tag: Context) {
        picasso.resumeTag(tag)
    }

    fun pauseTag(tag: Context) {
        picasso.pauseTag(tag)
    }

    fun cancelTag(tag: Context) {
        picasso.cancelTag(tag)
    }

    fun loadImage(url: String) {
        loadImage(url, null)
    }

    fun loadImage(url: String, callBack: Callback?) {
        if (TextUtils.isEmpty(url)) {
            return
        }
        picasso.load(url).fetch(callBack)
    }

    fun LoadImageWithTager(url: String, target: Target) {
        if (TextUtils.isEmpty(url)) {
            return
        }
        picasso.load(url).into(target)
    }
}