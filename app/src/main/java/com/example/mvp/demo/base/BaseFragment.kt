package com.example.mvp.demo.base

import android.app.AlertDialog
import android.os.Bundle
import com.example.mvp.demo.base.contract.Contract
import com.example.mvp.demo.common.utils.ImageLoader
import com.example.mvp.demo.common.utils.ToastUtils
import com.example.mvp.demo.common.widget.AlertDialogUtils
import com.trello.rxlifecycle2.components.RxFragment

/**
 * datetime：18-9-7
 * author：yilz
 */
abstract class BaseFragment<T : Contract.IIPresenter> : RxFragment(), Contract.IView {
    private var mAlertDialog: AlertDialog? = null
    protected lateinit var mPresenter: T

    protected val TAG by lazy { this.javaClass.toString() }


    override fun showLoading() {
        if (mAlertDialog != null && mAlertDialog!!.isShowing) {
            return
        }
        mAlertDialog = AlertDialogUtils.getAlertDialog(activity, interruptBack())
    }

    override fun hideLoading() {
        if (mAlertDialog != null && mAlertDialog!!.isShowing) {
            mAlertDialog?.dismiss()
            mAlertDialog = null
        }
    }

    open fun interruptBack(): Boolean {
        return false
    }

    override fun showMessage(message: CharSequence) {
        ToastUtils.showToast(message)
    }

    override fun showError(error: CharSequence) {

    }


    abstract fun initModeAndPresenter()

    abstract fun createPresenter(): T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = createPresenter()
        initModeAndPresenter()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.destroy()
        ImageLoader.cancelTag(TAG)
    }
}