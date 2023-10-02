package com.test.assignment.ui.adapters

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.assignment.R
import com.test.assignment.data.model.ShoppingItemModel
import com.test.assignment.databinding.ItemListViewBinding
import com.test.assignment.utils.SharedPref

class ItemListAdapter(
    private var categoryList: ArrayList<ShoppingItemModel>,
    private var context: Context,private var hasFavIcon : Boolean
) : RecyclerView.Adapter<ItemListAdapter.Pager2ViewHolder>() {
    private val viewWidth = 375
    private val viewHeight = 812
    private var windowWidth: Int = SharedPref.getInstance(context).getWidth(0)
    private var windowHeight: Int = SharedPref.getInstance(context).getHeight(0)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        val binding = ItemListViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return Pager2ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        Glide.with(context).load(categoryList[position].CONTENT_THUMB).centerCrop().into(holder.binding.bannerView)
        Glide.with(context).load(categoryList[position].CONTENT_THUMB).centerCrop().into(holder.binding.imBannerWithoutText)

        holder.binding.categoryName.text = categoryList[position].CONTENT_DESCRIPTION
        holder.binding.categoryName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10f)
        holder.binding.container.layoutParams.width = 140 * windowWidth/viewWidth

        if(categoryList[position].IS_FAV != null && categoryList[position].IS_FAV == true){
            Glide.with(context).load(R.drawable.fav_selected).optionalFitCenter().into(holder.binding.imFav)
        }else{
            Glide.with(context).load(R.drawable.fav_unselected).optionalFitCenter().into(holder.binding.imFav)
        }

        if(categoryList[position].PRICE != null) {
            holder.binding.categoryPrice.text = "RS "+categoryList[position].PRICE.toString()
            holder.binding.itemWithOutDescription.visibility = View.GONE
        }else{
            holder.binding.categoryPrice.visibility = View.GONE
            holder.binding.itemWithOutDescription.visibility = View.GONE
        }

        if(categoryList[position].IS_FAV == null){
            holder.binding.imFav.visibility = View.GONE
            holder.binding.container.layoutParams.height = 140 * windowHeight/viewHeight
            holder.binding.itemWithDescription.visibility = View.GONE
            holder.binding.itemWithOutDescription.visibility = View.VISIBLE
        }
        if(categoryList[position].CONTENT_DESCRIPTION == null) {
            holder.binding.categoryName.visibility = View.GONE
            holder.binding.itemWithDescription.visibility = View.GONE
            holder.binding.itemWithOutDescription.visibility = View.VISIBLE
        }

        if(hasFavIcon){
            holder.binding.imFav.visibility = View.VISIBLE
        }else{
            holder.binding.imFav.visibility = View.GONE
        }

    }

    private fun setImageViewMargins(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.setMargins(left, top, right, bottom)
        view.layoutParams = layoutParams
    }

    class Pager2ViewHolder(val binding: ItemListViewBinding) : RecyclerView.ViewHolder(binding.root) {

    }


}

