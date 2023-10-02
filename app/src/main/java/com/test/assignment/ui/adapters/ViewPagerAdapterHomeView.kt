package com.test.assignment.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.assignment.data.model.ShoppingItemModel
import com.test.assignment.databinding.ViewPagerItemHomeViewBinding
import com.test.assignment.utils.SharedPref

class ViewPagerAdapterHomeView(
    private var bannerList: ArrayList<ShoppingItemModel>,
    private var context: Context,private var isFromViewPager :Boolean
) : RecyclerView.Adapter<ViewPagerAdapterHomeView.Pager2ViewHolder>() {
    private val viewWidth = 375
    private val viewHeight = 812
    private var windowWidth: Int = SharedPref.getInstance(context).getWidth(0)
    private var windowHeight: Int = SharedPref.getInstance(context).getHeight(0)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        val binding = ViewPagerItemHomeViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return Pager2ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return bannerList.size
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        Glide.with(context).load(bannerList[position].CONTENT_THUMB).optionalFitCenter().into(holder.binding.bannerView)

        if(isFromViewPager){
            holder.binding.bannerView.setRadius(10f)
        }else{
            holder.binding.bannerView.setRadius(0f)
        }

    }

    private fun setImageViewMargins(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.setMargins(left, top, right, bottom)
        view.layoutParams = layoutParams
    }

    class Pager2ViewHolder(val binding: ViewPagerItemHomeViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }


}

