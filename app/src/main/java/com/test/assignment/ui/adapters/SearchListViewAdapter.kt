package com.test.assignment.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.assignment.data.model.SearchHistoryModel
import com.test.assignment.databinding.ItemSearchHistoryBinding
import com.test.assignment.utils.SharedPref

class SearchListViewAdapter(
    private var searchList: ArrayList<SearchHistoryModel>,
    private var context: Context,
) : RecyclerView.Adapter<SearchListViewAdapter.Pager2ViewHolder>() {
    private val viewWidth = 375
    private val viewHeight = 812
    private var windowWidth: Int = SharedPref.getInstance(context).getWidth(0)
    private var windowHeight: Int = SharedPref.getInstance(context).getHeight(0)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        val binding = ItemSearchHistoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return Pager2ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        holder.binding.title.text = searchList[position].TITLE

        val adapter = SearchHistoryItemListAdapter(searchList[position].viewAdapterArrayList,context)
        holder.binding.searchHistoryList.layoutManager = GridLayoutManager(context,1)
        holder.binding.searchHistoryList.adapter = adapter

    }

    class Pager2ViewHolder(val binding: ItemSearchHistoryBinding) : RecyclerView.ViewHolder(binding.root) {

    }


}

