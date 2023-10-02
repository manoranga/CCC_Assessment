package com.test.assignment.ui.base

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.test.assignment.R
import com.test.assignment.databinding.ActivityMain2Binding
import com.test.assignment.ui.fragments.home.HomeFragment
import com.test.assignment.ui.fragments.orders.OrdersFragment
import com.test.assignment.ui.fragments.shops.ShopsFragment
import com.test.assignment.utils.SharedPref


class MainActivity : BaseActivity(),HomeFragment.ScrollListener {
    private lateinit var binding: ActivityMain2Binding
    private var windowWidth: Int = 0
    private var windowHeight: Int = 0
    val fragmentHome = HomeFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        enableFullScreen()
        onClicks()
        initViews()

        fragmentHome.setListener(this)
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragmentHome).commit()


    }

    private fun initViews() {
        binding.searchView.searchBar.textClear.visibility = View.GONE

        windowWidth = SharedPref.getInstance(applicationContext).getWidth(0)
        windowHeight = SharedPref.getInstance(applicationContext).getHeight(0)

        binding.bottomActionBar.layoutParams.height = 89 * windowHeight / viewHeight
        binding.searchView.searchWithOutTitle.layoutParams.height = 110 * windowHeight / viewHeight
        binding.searchView.searchBarWithOutTitle.mainSearchBarContainer.layoutParams.height = 42 * windowHeight / viewHeight

        binding.searchView.searchWithTitle.visibility = View.GONE
        binding.searchView.searchWithOutTitle.visibility = View.VISIBLE

    }

    private fun onClicks() {
        binding.btnOrders.setOnClickListener {
            deselectIcons()
            replaceFragment(OrdersFragment())
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.imOrders.setColorFilter(getColor(R.color.view_pager_select_color))
            }
        }

        binding.btnHome.setOnClickListener {
            deselectIcons()
            fragmentHome.setListener(this)
            supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_fragment, R.anim.slide_out_fragment).replace(R.id.fragmentContainer, fragmentHome).commit()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.imHome.setColorFilter(getColor(R.color.view_pager_select_color))
            }
            binding.searchViewContainer.visibility = View.GONE
        }

        binding.btnShops.setOnClickListener {
            deselectIcons()
            replaceFragment(ShopsFragment())
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.imShops.setColorFilter(getColor(R.color.view_pager_select_color))
            }
        }

        binding.searchView.searchBarWithOutTitle.filterIcon.setOnClickListener{
//            fragmentHome.openSearchFragment()
//            binding.searchViewContainer.visibility = View.GONE
        }

    }

    private fun deselectIcons(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.imHome.setColorFilter(getColor(R.color.register_view_description_color))
            binding.imShops.setColorFilter(getColor(R.color.register_view_description_color))
            binding.imOrders.setColorFilter(getColor(R.color.register_view_description_color))
            binding.imCart.setColorFilter(getColor(R.color.register_view_description_color))
            binding.imProfile.setColorFilter(getColor(R.color.register_view_description_color))
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_in_fragment, R.anim.slide_out_fragment)
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
        binding.searchViewContainer.visibility = View.GONE
    }

    override fun onScrollDY(dy: Int) {
        Log.d("Scroll", "Vertical scroll distance: $dy")
        if(dy>620){
            binding.searchViewContainer.visibility = View.VISIBLE
            //applicationContext?.let { it -> SlidingOptions.slideDown(binding.searchViewContainer, it) }
        }else{
            binding.searchViewContainer.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        enableFullScreen()
    }

    override fun onBackPressed() {
        enableFullScreen()
    }
}