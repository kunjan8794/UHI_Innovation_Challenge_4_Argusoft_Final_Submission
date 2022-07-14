package com.uhi.ui.dashboard

import android.location.Location
import androidx.activity.result.contract.ActivityResultContracts
import com.uhi.databinding.ActivityHomeBinding
import com.uhi.ui.common.base.BaseActivity
import com.uhi.utils.common.LocationUtils
import com.uhi.utils.extention.timber
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity<ActivityHomeBinding>() {

    private val resolutionForResult =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { activityResult ->
            LocationUtils.onActivityResult(LocationUtils.REQUEST_CHECK_SETTINGS, activityResult.resultCode, activityResult.data)
        }

    override fun initView() {
        // Binding object with view
        binding.navHostFragment
        LocationUtils.fetchLocation(this,resolutionForResult, object : LocationUtils.LocationListener {
            override fun onStartLocationFetch() {
                "onStartLocationFetch".timber()
            }

            override fun onLocationChanged(location: Location) {
                "Location: $location".timber()
            }
        })
    }

    override fun initListener() {
    }

    override fun initObserver() {
    }

    override fun onResume() {
        super.onResume()
        appInAppUpdateCheck()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        LocationUtils.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
