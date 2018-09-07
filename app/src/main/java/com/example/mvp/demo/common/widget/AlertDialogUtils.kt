package com.example.mvp.demo.common.widget

import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.KeyEvent
import com.example.mvp.demo.R

/**
 * datetime：18-9-7
 * author：yilz
 */
object AlertDialogUtils {
    fun getAlertDialog(context: Context, interrupted: Boolean): AlertDialog? {
        if (context == null) {
            return null
        }
        var alertDialog = AlertDialog.Builder(context).create()
        alertDialog.window?.setBackgroundDrawable(object : ColorDrawable() {

        })
        alertDialog.setCancelable(false)
        alertDialog.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK && interrupted) {
                return@setOnKeyListener true
            }
            alertDialog.dismiss()
            return@setOnKeyListener false
        }
        alertDialog.show()
        alertDialog.setContentView(R.layout.loading_alert)
        alertDialog.setCanceledOnTouchOutside(false)
        return alertDialog
    }
}