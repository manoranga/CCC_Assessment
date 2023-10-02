package com.test.assignment.ui.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.test.assignment.databinding.FragmentEnableNotificationBinding


class EnableNotificationsFragment : BaseFragment() {
    private var _binding: FragmentEnableNotificationBinding? = null
    private val binding get() = _binding!!
    private var listener: EnableNotificationFragmentListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentEnableNotificationBinding.inflate(inflater, container, false)
        initView()
        onClicks()

        return binding.root
    }

    private fun onClicks() {
        binding.btnEnableNotification.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
           listener?.onMethodCallFromEnableNotificationFragment()
        }

    }


    private fun initView() {

        binding.orderDispatchView.layoutParams.height = 95 * windowHeight / viewHeight
        binding.orderDispatchView.layoutParams.width = 308 * windowWidth / viewWidth

        setViewMargins(binding.orderDispatchView,0,155*windowHeight/viewHeight,0,0)

        binding.appIcon.layoutParams.height = 52 * windowHeight / viewHeight
        binding.appIcon.layoutParams.width = 52 * windowWidth / viewWidth


        setViewMargins(binding.appIcon,16*windowWidth/viewWidth,21*windowHeight/viewHeight,0,21*windowHeight/viewHeight)
        setViewMargins(binding.orderDispatchTextViews,14 * windowWidth/viewWidth,21*windowHeight/viewHeight,0,21*windowHeight/viewHeight)
        setViewMargins(binding.textStayLoop,0,142 * windowHeight/viewHeight,0,0)
        setViewMargins(binding.textGetAlert,32 * windowWidth / viewWidth,26*windowHeight/viewHeight,32 * windowWidth / viewWidth,0)

        val layoutParamsEnableNotification = LinearLayout.LayoutParams(249 * windowWidth / viewWidth, 46 * windowHeight / viewHeight)
        layoutParamsEnableNotification.gravity = Gravity.CENTER
        layoutParamsEnableNotification.setMargins(0, 111 * windowHeight / viewHeight, 0, 0)
        binding.btnEnableNotification.layoutParams = layoutParamsEnableNotification

        setViewMargins(binding.textNotNow,0,37*windowHeight/viewHeight,0,0)
    }

    interface EnableNotificationFragmentListener {
        fun onMethodCallFromEnableNotificationFragment()
    }

    fun setListener(listener: EnableNotificationFragmentListener) {
        this.listener = listener
    }
}