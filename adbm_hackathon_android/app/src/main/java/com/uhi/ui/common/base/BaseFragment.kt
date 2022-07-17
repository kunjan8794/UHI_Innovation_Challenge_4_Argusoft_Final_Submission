package com.uhi.ui.common.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.uhi.R
import com.uhi.data.local.pref.Preference
import com.uhi.ui.dashboard.DashboardActivity
import com.uhi.utils.extention.hideKeyboard
import com.uhi.utils.extention.onViewBinding
import javax.inject.Inject

abstract class BaseFragment<B : ViewBinding> : Fragment(), View.OnClickListener {

    @Inject
    lateinit var preference: Preference
    private var _binding: B? = null
    protected val binding
        get() = _binding
            ?: throw RuntimeException("Should only use binding after onCreateView and before onDestroyView")
    abstract fun initView()
    abstract fun initListener()
    abstract fun initObserver()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = onViewBinding(inflater, container)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        initObserver()
    }

    override fun onClick(view: View?) {
        hideKeyboard(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    public fun restartApp() {
        val intent = Intent(activity, DashboardActivity::class.java)
        //intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        activity?.finish()
        activity?.startActivity(intent)

    }
}