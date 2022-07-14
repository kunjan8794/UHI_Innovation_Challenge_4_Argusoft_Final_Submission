package com.uhi.ui.splash

import android.content.Intent
import com.uhi.databinding.ActivitySplashBinding
import com.uhi.ui.common.base.BaseActivity
import com.uhi.ui.dashboard.DashboardActivity


class SplashScreenActivity : BaseActivity<ActivitySplashBinding>() {

    override fun initView() {
        startActivity(Intent(this@SplashScreenActivity, DashboardActivity::class.java))
        finish()
    }

    override fun initListener() {
    }

    override fun initObserver() {
    }
}