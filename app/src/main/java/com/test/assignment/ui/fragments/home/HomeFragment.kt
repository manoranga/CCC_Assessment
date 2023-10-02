package com.test.assignment.ui.fragments.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.test.assignment.R
import com.test.assignment.data.managers.DataManager
import com.test.assignment.databinding.FragmentHomeBinding
import com.test.assignment.ui.adapters.CategoryAdapterHomeView
import com.test.assignment.ui.adapters.MainSectionsRecycleAdapterHomeView
import com.test.assignment.ui.adapters.ViewPagerAdapterHomeView
import com.test.assignment.ui.base.location.LocationChangesActivity
import com.test.assignment.ui.fragments.BaseFragment
import com.test.assignment.utils.CustomNestedScrollView


class HomeFragment : BaseFragment() , CustomNestedScrollView.NestedScrollListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewBannerAdapter : ViewPagerAdapterHomeView
    private lateinit var homeViewCategoryAdapter : CategoryAdapterHomeView
    private lateinit var mainSectionsRecycleAdapterHomeView: MainSectionsRecycleAdapterHomeView

    private var listener: ScrollListener? = null

    private val autoSwapInterval: Long = 3000
    val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        DataManager.getInstance(requireContext()).getPostListHomeView()
        initView()
        onClicks()

        return binding.root
    }


    private fun onClicks() {
        binding.searchBar.filterIcon.setOnClickListener {
           openSearchFragment()
        }

        binding.locationBarView.mainSearchBarContainer.setOnClickListener{
            val intent = Intent(activity, LocationChangesActivity::class.java)
            startActivity(intent)
            activity?.overridePendingTransition(R.anim.slide_up, R.anim.slide_down)
        }

    }

    fun openSearchFragment() {
        val newFragment = SearchFragment()
        val transaction = parentFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down)
        transaction.replace(R.id.fragment_search_container, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun initView() {
        homeViewBannerAdapter = ViewPagerAdapterHomeView(DataManager.getInstance(requireContext()).bannerList,requireContext(),false)
        homeViewCategoryAdapter = CategoryAdapterHomeView(DataManager.getInstance(requireContext()).categoryList,requireContext())
        mainSectionsRecycleAdapterHomeView = MainSectionsRecycleAdapterHomeView(DataManager.getInstance(requireContext()).mainSectionModels,requireContext())

        binding.mainViewPager.adapter = homeViewBannerAdapter
        binding.mainViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.pagerIndicator.setViewPager(binding.mainViewPager)
        binding.mainViewPager.layoutParams.height = 328 * windowHeight/viewHeight
        autoSwapBannerView()

        setViewMargins(binding.pagerIndicatorContainer,0,265 * windowHeight/viewHeight,0,0)
        setViewMargins(binding.userAddressContainer,0,43 * windowHeight/viewHeight,0,0)
        setViewMargins(binding.homeViewCategoryParentContainer,0,283 * windowHeight/viewHeight,0,0)

        binding.homeViewCategoryContainer.layoutParams.width = 335 * windowWidth / viewWidth
        binding.homeViewCategoryContainer.layoutParams.height = 227 * windowHeight / viewHeight

        binding.locationBarView.mainSearchBarContainer.layoutParams.width = 335 * windowWidth / viewWidth
        binding.locationBarView.mainSearchBarContainer.layoutParams.height = 46 * windowHeight/viewHeight

        val layoutParamsVerifyBtn = LinearLayout.LayoutParams(294 * windowWidth / viewWidth, 42 * windowHeight / viewHeight)
        layoutParamsVerifyBtn.gravity = Gravity.CENTER
        layoutParamsVerifyBtn.setMargins(20 * windowWidth / viewWidth, 20 * windowHeight / viewHeight, 20 * windowWidth / viewWidth, 20 * windowHeight / viewHeight)
        binding.searchBar.mainSearchBarContainer.layoutParams = layoutParamsVerifyBtn


        // category recycle view
        binding.rvCategory.layoutManager = GridLayoutManager(requireContext(),4)
        binding.rvCategory.adapter = homeViewCategoryAdapter

        //bottom recycle view init
        binding.mainRecycleView.layoutManager = GridLayoutManager(requireContext(),1)
        binding.mainRecycleView.adapter = mainSectionsRecycleAdapterHomeView
        setViewMargins(binding.mainRecycleView,0,528 * windowHeight/viewHeight,0,0)

        binding.nestedScrollView.setNestedScrollListener(this)

        binding.searchBar.textClear.visibility = View.GONE

    }

    private fun autoSwapBannerView(){
        val autoSwapRunnable: Runnable = object : Runnable {
            override fun run() {
                val currentItem = binding.mainViewPager.currentItem
                val nextItem = (currentItem + 1) % homeViewBannerAdapter.itemCount
                binding.mainViewPager.currentItem = nextItem
                handler.postDelayed(this, autoSwapInterval)
            }
        }

        handler.postDelayed(autoSwapRunnable, autoSwapInterval)
    }

    interface ScrollListener {
        fun onScrollDY(dy: Int)
    }

    fun setListener(listener: ScrollListener) {
        this.listener = listener
    }

    override fun onScroll(dy: Int) {
        listener?.onScrollDY(dy)
    }

}