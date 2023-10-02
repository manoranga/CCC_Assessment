package com.test.assignment.ui.fragments

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.test.assignment.utils.SharedPref

open class BaseFragment : Fragment() {
    val viewWidth = 375
    val viewHeight = 812
    var windowWidth: Int = SharedPref.getInstance(activity).getWidth(0)
    var windowHeight: Int = SharedPref.getInstance(activity).getHeight(0)



    fun setViewMargins(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.setMargins(left, top, right, bottom)
        view.layoutParams = layoutParams
    }

    fun enableFullScreen() {
        requireActivity().window.decorView.apply {
            // Hide both the navigation bar and the status bar.
            systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }
}