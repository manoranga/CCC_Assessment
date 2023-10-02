package com.test.assignment.ui.adapters

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.assignment.data.model.ShoppingItemModel
import com.test.assignment.databinding.CategoryItemHomeViewBinding
import com.test.assignment.utils.SharedPref

class CategoryAdapterHomeView(
    private var categoryList: ArrayList<ShoppingItemModel>,
    private var context: Context,
) : RecyclerView.Adapter<CategoryAdapterHomeView.Pager2ViewHolder>() {
    private val viewWidth = 375
    private val viewHeight = 812
    private var windowWidth: Int = SharedPref.getInstance(context).getWidth(0)
    private var windowHeight: Int = SharedPref.getInstance(context).getHeight(0)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        val binding = CategoryItemHomeViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return Pager2ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        Glide.with(context).load(categoryList[position].CONTENT_THUMB).optionalFitCenter().into(holder.binding.bannerView)

        holder.binding.categoryName.text = categoryList[position].CONTENT_DESCRIPTION
        holder.binding.categoryName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10f)

        holder.binding.container.layoutParams.height = ((227 * windowHeight / viewHeight)*2/3)/2
//        holder.binding.bannerView.layoutParams.width = 41*windowWidth/viewWidth

    }

    private fun setImageViewMargins(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.setMargins(left, top, right, bottom)
        view.layoutParams = layoutParams
    }

    class Pager2ViewHolder(val binding: CategoryItemHomeViewBinding) : RecyclerView.ViewHolder(binding.root) {

    }


}

