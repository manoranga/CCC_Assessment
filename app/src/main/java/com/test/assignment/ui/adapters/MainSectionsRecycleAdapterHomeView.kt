package com.test.assignment.ui.adapters

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.test.assignment.data.model.SectionModels
import com.test.assignment.databinding.RvItemHomeMainBinding
import com.test.assignment.helper.SectionType
import com.test.assignment.utils.SharedPref

class MainSectionsRecycleAdapterHomeView(
    private var sectionModels: ArrayList<SectionModels>,
    private var context: Context,
) : RecyclerView.Adapter<MainSectionsRecycleAdapterHomeView.Pager2ViewHolder>() {
    private val viewWidth = 375
    private val viewHeight = 812
    private var windowWidth: Int = SharedPref.getInstance(context).getWidth(0)
    private var windowHeight: Int = SharedPref.getInstance(context).getHeight(0)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        val binding = RvItemHomeMainBinding.inflate(LayoutInflater.from(context), parent, false)
        return Pager2ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return sectionModels.size
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        holder.bindData(sectionModels[position], context)

        holder.binding.viewPager.layoutParams.height = 140 * windowHeight / viewHeight
        holder.binding.viewBannerItem.layoutParams.height = 140 * windowHeight / viewHeight
    }

    private fun setImageViewMargins(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.setMargins(left, top, right, bottom)
        view.layoutParams = layoutParams
    }

    class Pager2ViewHolder(val binding: RvItemHomeMainBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var homeViewCategoryAdapter : ItemListAdapter
        private lateinit var homeViewShopAdapter : ItemListAdapter
        private lateinit var viewBannerAdapter : ViewPagerAdapterHomeView
        private val autoSwapInterval: Long = 6000
        val handler = Handler(Looper.getMainLooper())


        fun bindData(sectionModels: SectionModels,context: Context){
            hideAllViews()

            if(sectionModels.arrayList!=null) {
                homeViewCategoryAdapter = ItemListAdapter(sectionModels.arrayList, context,true)
                homeViewShopAdapter = ItemListAdapter(sectionModels.arrayList, context,false)
            }
            if(sectionModels.viewAdapterArrayList!=null) {
                viewBannerAdapter = ViewPagerAdapterHomeView(sectionModels.viewAdapterArrayList, context,true)
            }

            when(sectionModels.CELL_TYPE){
                SectionType.ALL_ITEM_LIST->{
                    binding.rvItems.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
                    binding.rvItems.adapter = homeViewCategoryAdapter
                    binding.viewBannerItem.visibility = View.GONE
                    binding.viewItemList.visibility = View.VISIBLE
                    binding.tvTitle.text = sectionModels.TITLE
                }
                SectionType.ALL_SHOP_LIST->{
                    binding.rvItems.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
                    binding.rvItems.adapter = homeViewShopAdapter
                    binding.viewBannerItem.visibility = View.GONE
                    binding.viewItemList.visibility = View.VISIBLE
                    binding.tvTitle.text = sectionModels.TITLE
                }
                SectionType.VIEW_PAGER->{
                    binding.viewPagerContainer.visibility = View.VISIBLE
                    binding.viewPager.adapter = viewBannerAdapter
                    binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

                    binding.pagerIndicator.setViewPager(binding.viewPager)
                    autoSwapBannerView()

                }
                SectionType.NONE->{

                }SectionType.ONE_BANNER->{
                binding.viewBannerItem.visibility = View.VISIBLE
                binding.viewItemList.visibility = View.GONE

                Glide.with(context).load(sectionModels.singleImageShoppingItem).optionalFitCenter().into(binding.imBanner)
                }

                null -> TODO()
            }
        }

        private fun hideAllViews(){
            binding.viewItemList.visibility = View.GONE
            binding.viewBannerItem.visibility = View.GONE
            binding.viewPagerContainer.visibility = View.GONE
        }

        private fun autoSwapBannerView(){
            val autoSwapRunnable: Runnable = object : Runnable {
                override fun run() {
                    val currentItem = binding.viewPager.currentItem
                    val nextItem = (currentItem + 1) % viewBannerAdapter.itemCount
                    binding.viewPager.currentItem = nextItem
                    handler.postDelayed(this, autoSwapInterval)
                }
            }

            handler.postDelayed(autoSwapRunnable, autoSwapInterval)
        }
    }




}

