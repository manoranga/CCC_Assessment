package com.test.assignment.ui.base

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.viewpager2.widget.ViewPager2
import com.test.assignment.R
import com.test.assignment.databinding.ActivityStartUpActvityBinding
import com.test.assignment.ui.adapters.ViewPagerAdapterForStartupActivity
import com.test.assignment.ui.fragments.EnableNotificationsFragment
import com.test.assignment.ui.fragments.UserRegisterFragment
import com.test.assignment.utils.SharedPref

class StartUpActivity : BaseActivity(), UserRegisterFragment.UserRegisterFragmentListener, EnableNotificationsFragment.EnableNotificationFragmentListener {
    private lateinit var binding: ActivityStartUpActvityBinding
    private var titleList = ArrayList<String>()
    private var descriptionList = ArrayList<String>()
    private var windowWidth: Int = 0
    private var windowHeight: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartUpActvityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        postList()

        enableFullScreen()
        showSplashAnim()

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        windowWidth = displayMetrics.widthPixels;
        windowHeight = displayMetrics.heightPixels;

        SharedPref.getInstance(applicationContext).putWidth(windowWidth)
        SharedPref.getInstance(applicationContext).putHeight(windowHeight)

        binding.mainViewPager.adapter = ViewPagerAdapterForStartupActivity(titleList, descriptionList, applicationContext)
        binding.mainViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.pagerIndicator.setViewPager(binding.mainViewPager)
        binding.splashView.visibility = View.VISIBLE

        initView()
        setViewOnClickListener()

    }

    private fun setViewOnClickListener() {
        binding.btnGetStarted.setOnClickListener {
            val transparentFragment = UserRegisterFragment()
            transparentFragment.setListener(this)
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.register_container, transparentFragment).commit()
        }

    }

    private fun initView() {
        binding.mainViewAppIcon.layoutParams.width = 146 * windowWidth / viewWidth
        binding.mainViewAppIcon.layoutParams.height = 69 * windowHeight / viewHeight

        setImageViewMargins(binding.mainViewAppIcon, 0, 120 * windowHeight / viewHeight, 0, 0)
        setImageViewMargins(binding.pagerIndicator, 0, 48 * windowHeight / viewHeight, 0, 0)
        setImageViewMargins(
            binding.btnGetStarted,
            95 * windowWidth / viewWidth,
            64 * windowHeight / viewHeight,
            95 * windowWidth / viewWidth,
            0
        )

        setImageViewMargins(binding.btnSkip, 0, 37 * windowHeight / viewHeight, 0, 0)

        binding.btnGetStarted.layoutParams.width = 184 * windowWidth / viewWidth
        binding.btnGetStarted.layoutParams.height = 46 * windowHeight / viewHeight

    }

    private fun addList(title: String, description: String) {
        titleList.add(title)
        descriptionList.add(description)
    }

    private fun postList() {
        for (i in 1..3) {
            addList(
                "Welcome to\n" + "The Shopping Mall $i ",
                "Order a wide range of selections from your\n" + "favorite brand or shops. $i "
            )
        }
    }


    private fun showSplashAnim() {
        val slideDownAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_anim)

        slideDownAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                binding.splashView.visibility = View.GONE
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })
        binding.appIcon.startAnimation(slideDownAnimation)
    }

    override fun onMethodCallFromUserRegisterFragment() {
        Handler(Looper.getMainLooper()).postDelayed({
            val transparentFragment = EnableNotificationsFragment()
            transparentFragment.setListener(this)
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.enable_notification_container, transparentFragment).commit()
        }, 500)
    }

    override fun onMethodCallFromEnableNotificationFragment() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}