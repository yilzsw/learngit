package com.example.mvp.demo.base.contract

import com.example.mvp.demo.base.BaseMode

/**
 * datetime：18-9-6
 * author：yilz
 */
interface Contract {
    interface IView {
        /**
         * 显示加载
         */
        fun showLoading()

        /**
         * 隐藏加载
         */
        fun hideLoading()

        /**
         * 显示信息
         *
         * @param message 消息内容, 不能为 `null`
         */
        fun showMessage(message: CharSequence)

        fun showError(error: CharSequence)
    }

    interface IIPresenter {
        fun initModeAndView(iView: IView, newMode: BaseMode)

        fun destroy()
    }
}