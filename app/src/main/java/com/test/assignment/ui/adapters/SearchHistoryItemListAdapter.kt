package com.test.assignment.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.assignment.databinding.SearchListItemBinding

class SearchHistoryItemListAdapter(
    private var searchHistoryItems: ArrayList<String>,
    private var context: Context,
) : RecyclerView.Adapter<SearchHistoryItemListAdapter.Pager2ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        val binding = SearchListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return Pager2ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return searchHistoryItems.size
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
       holder.binding.textView.text = searchHistoryItems[position]

    }

    private fun setImageViewMargins(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.setMargins(left, top, right, bottom)
        view.layoutParams = layoutParams
    }

    class Pager2ViewHolder(val binding: SearchListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }


}

