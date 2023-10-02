package com.test.assignment.ui.base.location

import android.os.Bundle
import com.test.assignment.databinding.ActivityLocationChangesBinding
import com.test.assignment.ui.base.BaseActivity
import com.test.assignment.utils.SharedPref

class LocationChangesActivity : BaseActivity() {
    private lateinit var binding: ActivityLocationChangesBinding
    private var windowWidth: Int = 0
    private var windowHeight: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLocationChangesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableFullScreen()

        initViews()
        onClick()
    }

    private fun onClick() {
        binding.btnBack.setOnClickListener{
            finish()
        }
    }

    private fun initViews() {
        windowWidth = SharedPref.getInstance(applicationContext).getWidth(0)
        windowHeight = SharedPref.getInstance(applicationContext).getHeight(0)

        binding.editTextContainer1.layoutParams.height = 52 * windowHeight / viewHeight
        binding.editTextContainer2.layoutParams.height = 52 * windowHeight / viewHeight
        binding.editTextContainer3.layoutParams.height = 52 * windowHeight / viewHeight
        binding.editTextContainer4.layoutParams.height = 52 * windowHeight / viewHeight

        binding.mapContainer.layoutParams.height = 200 * windowHeight / viewHeight

        setImageViewMargins(binding.btnGetStarted, 95 * windowWidth / viewWidth, 64 * windowHeight / viewHeight, 95 * windowWidth / viewWidth, 0)

        binding.btnGetStarted.layoutParams.width = 184 * windowWidth / viewWidth
        binding.btnGetStarted.layoutParams.height = 46 * windowHeight / viewHeight

        setImageViewMargins(binding.btnSkip, 0, 37 * windowHeight / viewHeight, 0, 0)
        setImageViewMargins(binding.mainViewContainer, 0, 41 * windowHeight / viewHeight, 0, 0)

    }
}