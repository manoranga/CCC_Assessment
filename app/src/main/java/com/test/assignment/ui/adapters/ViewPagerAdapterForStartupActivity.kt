package com.test.assignment.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.assignment.databinding.ViewPagerItemStartViewBinding
import com.test.assignment.utils.SharedPref

class ViewPagerAdapterForStartupActivity(
    private var title: ArrayList<String>,
    private var description: ArrayList<String>,
    context: Context,
) : RecyclerView.Adapter<ViewPagerAdapterForStartupActivity.Pager2ViewHolder>() {
    private val viewWidth = 375
    private val viewHeight = 812
    private var windowWidth: Int = SharedPref.getInstance(context).getWidth(0)
    private var windowHeight: Int = SharedPref.getInstance(context).getHeight(0)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        val binding = ViewPagerItemStartViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Pager2ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return title.size
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        holder.binding.title.text = title[position]
        holder.binding.description.text = description[position]

        setImageViewMargins(holder.binding.title, 0, 259 * windowHeight / viewHeight, 0, 0)
        setImageViewMargins(holder.binding.description, 0, 17 * windowHeight / viewHeight, 0, 0)
    }

    fun setImageViewMargins(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.setMargins(left, top, right, bottom)
        view.layoutParams = layoutParams
    }

    class Pager2ViewHolder(val binding: ViewPagerItemStartViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }


}

