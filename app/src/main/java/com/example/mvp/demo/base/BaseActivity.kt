package com.example.mvp.demo.base

import android.app.AlertDialog
import android.os.Bundle
import android.view.WindowManager
import com.example.mvp.demo.base.contract.Contract
import com.example.mvp.demo.common.AppManager
import com.example.mvp.demo.common.utils.ImageLoader
import com.example.mvp.demo.common.utils.ToastUtils
import com.example.mvp.demo.common.widget.AlertDialogUtils
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

abstract class BaseActivity<T : Contract.IIPresenter> : RxAppCompatActivity(), Contract.IView {

    protected lateinit var mPresenter: T

    protected val TAG by lazy { this.javaClass.toString()}

    private var mAlertDialog: AlertDialog? = null
    override fun showLoading() {
        if (mAlertDialog != null && mAlertDialog!!.isShowing) {
            return
        }
        mAlertDialog = AlertDialogUtils.getAlertDialog(this, interruptBack())
    }

    override fun hideLoading() {
        if (mAlertDialog != null && mAlertDialog!!.isShowing) {
            mAlertDialog?.hide()
            mAlertDialog = null
        }
    }

    open fun interruptBack(): Boolean {
        return false
    }

    abstract fun initModeAndPresenter()

    abstract fun createPresenter(): T

    open fun isSingle(): Boolean {
        return true
    }

    override fun showMessage(message: CharSequence) {
        ToastUtils.showToast(message)
    }

    override fun showError(error: CharSequence) {

    }

    private fun translucentStatusBar() {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        translucentStatusBar()
        if (isSingle()) {
            AppManager.makeActivityInSingle(this)
        }
        AppManager.addActivity(this)
        mPresenter = createPresenter()
        initModeAndPresenter()
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.finishActivity(this)
        mPresenter?.destroy()
        ImageLoader.cancelTag(TAG)
    }
}