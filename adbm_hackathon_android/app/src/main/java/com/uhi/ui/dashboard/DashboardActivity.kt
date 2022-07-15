package com.uhi.ui.dashboard

import android.location.Location
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.uhi.R
import com.uhi.databinding.ActivityHomeBinding
import com.uhi.ui.common.base.BaseActivity
import com.uhi.utils.common.LocationUtils
import com.uhi.utils.extention.timber
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity<ActivityHomeBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
            navHostFragment.navController.setGraph(R.navigation.home,intent.extras)
        }
    }

    override fun initView() {

    }

    override fun initListener() {
    }

    override fun initObserver() {
    }

}
