package com.example.mvp.demo.common.utils

import android.widget.Toast
import com.example.mvp.demo.Application

/**
 * datetime：18-9-7
 * author：yilz
 */
object ToastUtils {
    fun showToast(message: CharSequence) {
        Toast.makeText(Application.getApplication(), message, Toast.LENGTH_SHORT).show()
    }
}