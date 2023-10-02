package com.test.assignment.ui.fragments.home

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import com.test.assignment.R
import com.test.assignment.data.managers.DataManager
import com.test.assignment.databinding.FragmentSearchBinding
import com.test.assignment.ui.adapters.*
import com.test.assignment.ui.fragments.BaseFragment


class SearchFragment : BaseFragment() {
    private var _binding: FragmentSearchBinding? = null
    val binding get() = _binding!!
    private lateinit var searchViewCategoryAdapter: CategoryAdapterSearchView
    private lateinit var searchHistoryListAdapter: SearchListViewAdapter
    private lateinit var searchResultListAdapter : ItemListAdapter

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
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        initLayout()
        onClicks()
        return binding.root
    }


    private fun onClicks() {
        binding.searchView.btnBack.setOnClickListener {
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_down, R.anim.slide_up)

            fragmentManager.popBackStack()

            transaction.commit()
        }

        binding.searchView.searchBar.etSearch.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    binding.searchHistoryContainer.visibility = View.VISIBLE
                }
            }

        binding.searchView.searchBar.etSearch.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER
            ) {

                onDoneAction()
                return@setOnEditorActionListener true
            }
            false
        }

        binding.searchView.searchBar.filterIcon.setOnClickListener{
          binding.searchResultsContainer.visibility = View.VISIBLE
          binding.searchView.searchBar.filterIcon.visibility = View.GONE
          binding.searchView.searchBar.textClear.visibility = View.VISIBLE
          binding.searchView.searchBarTitle.text = requireActivity().getString(R.string.searches)
        }

        binding.searchView.searchBar.textClear.setOnClickListener{
            binding.searchResultsContainer.visibility = View.GONE
            onDoneAction()
            binding.searchView.searchBar.filterIcon.visibility = View.VISIBLE
            binding.searchView.searchBar.textClear.visibility = View.GONE
            binding.searchView.searchBar.etSearch.clearFocus()
            binding.searchView.searchBarTitle.text = requireActivity().getString(R.string.what_are_you_looking)
        }
    }

    private fun onDoneAction() {
        binding.searchHistoryContainer.visibility = View.GONE
        enableFullScreen()
    }

    private fun initLayout() {
        binding.searchView.searchWithTitle.visibility = View.VISIBLE
        binding.searchView.searchWithOutTitle.visibility = View.GONE
        binding.searchView.searchBar.textClear.visibility = View.GONE

        binding.searchView.searchWithTitle.layoutParams.height = 145 * windowHeight / viewHeight
        binding.searchView.searchBar.mainSearchBarContainer.layoutParams.height =
            42 * windowHeight / viewHeight

        // search view
        searchViewCategoryAdapter = CategoryAdapterSearchView(DataManager.getInstance(requireContext()).getCategoryListSearchView(), requireContext())
        binding.rvCategory.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCategory.adapter = searchViewCategoryAdapter

        // search history
        searchHistoryListAdapter = SearchListViewAdapter(DataManager.getInstance(requireContext()).getSearchHistoryList(), requireContext())
        binding.rvSearchHistory.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.rvSearchHistory.adapter = searchHistoryListAdapter

        //search result
        searchResultListAdapter = ItemListAdapter(DataManager.getInstance(requireContext()).getSearchResultsList(), requireContext(),false)
        binding.rvSearchResults.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvSearchResults.adapter = searchResultListAdapter

    }

}


