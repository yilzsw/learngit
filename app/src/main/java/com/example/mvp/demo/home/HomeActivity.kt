package com.example.mvp.demo.home

import android.os.Bundle
import com.example.mvp.demo.R
import com.example.mvp.demo.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity<HomePresenter>() {
    override fun initModeAndPresenter() {
        mPresenter.initModeAndView(this, HomeMode())
    }

    override fun createPresenter(): HomePresenter {
        return HomePresenter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        mPresenter.initData<String>()
        nv_home_settings.setNavigationItemSelectedListener {
            showMessage(it.title.toString())
            return@setNavigationItemSelectedListener true
        }
    }
}
