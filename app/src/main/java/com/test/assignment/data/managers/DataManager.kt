package com.test.assignment.data.managers

import android.content.Context
import com.test.assignment.R
import com.test.assignment.data.model.SearchHistoryModel
import com.test.assignment.data.model.SectionModels
import com.test.assignment.data.model.ShoppingItemModel
import com.test.assignment.helper.SectionType

class DataManager private constructor(private val context: Context) {
    var bannerList = ArrayList<ShoppingItemModel>()
    var categoryList = ArrayList<ShoppingItemModel>()
    var sellItemList = ArrayList<ShoppingItemModel>()
    var shopItemList = ArrayList<ShoppingItemModel>()
    var mainSectionModels = ArrayList<SectionModels>()
    var viewPagerBannerList = ArrayList<ShoppingItemModel>()
    var isFirstTime: Boolean = true

    init {
    }

    companion object {
        @Volatile
        private var instance: DataManager? = null

        // Create and provide the instance
        fun getInstance(context: Context): DataManager {
            return instance ?: synchronized(this) {
                instance ?: DataManager(context.applicationContext).also { instance = it }
            }
        }
    }
    fun getPostListHomeView() {
        if (isFirstTime) {
            bannerList.add(
                ShoppingItemModel(
                    "",
                    "",
                    "",
                    R.drawable.banner_1,
                    SectionType.NONE,
                    null,
                    false
                )
            )
            bannerList.add(
                ShoppingItemModel(
                    "",
                    "",
                    "",
                    R.drawable.banner_2,
                    SectionType.NONE,
                    null,
                    false
                )
            )
            bannerList.add(
                ShoppingItemModel(
                    "",
                    "",
                    "",
                    R.drawable.banner_3,
                    SectionType.NONE,
                    null,
                    false
                )
            )


            categoryList.add(
                ShoppingItemModel(
                    "", "", "Fashion",
                    R.drawable.fashion,
                    SectionType.NONE, null, false
                )
            )
            categoryList.add(
                ShoppingItemModel(
                    "", "", "Electronics",
                    R.drawable.electronics,
                    SectionType.NONE, null, false
                )
            )
            categoryList.add(
                ShoppingItemModel(
                    "", "", "Home Garden",
                    R.drawable.home_gardens,
                    SectionType.NONE, null, false
                )
            )
            categoryList.add(
                ShoppingItemModel(
                    "", "", "Beauty",
                    R.drawable.beauty,
                    SectionType.NONE, null, false
                )
            )
            categoryList.add(
                ShoppingItemModel(
                    "", "", "Toyes",
                    R.drawable.toyes,
                    SectionType.NONE, null, false
                )
            )
            categoryList.add(
                ShoppingItemModel(
                    "", "", "Jewelry",
                    R.drawable.jewelry,
                    SectionType.NONE, null, false
                )
            )
            categoryList.add(
                ShoppingItemModel(
                    "", "", "Health Care",
                    R.drawable.health_care,
                    SectionType.NONE, null, false
                )
            )
            categoryList.add(
                ShoppingItemModel(
                    "", "", "Foods",
                    R.drawable.foods,
                    SectionType.NONE, null, false
                )
            )


            sellItemList.add(
                ShoppingItemModel(
                    "", "", "Mi Band 5 Smart Bracelet Standard",
                    R.drawable.item_1,
                    SectionType.ALL_ITEM_LIST, 3000, false
                )
            )
            sellItemList.add(
                ShoppingItemModel(
                    "", "", "Joyroom Wireless In-Ear Headset",
                    R.drawable.item_2,
                    SectionType.ALL_ITEM_LIST, 5000, true
                )
            )
            sellItemList.add(
                ShoppingItemModel(
                    "", "", "Mi Band 5 Smart Bracelet Standard",
                    R.drawable.item_1,
                    SectionType.ALL_ITEM_LIST, 3000, false
                )
            )
            sellItemList.add(
                ShoppingItemModel(
                    "", "", "Mi Band 5 Smart Bracelet Standard",
                    R.drawable.item_2,
                    SectionType.ALL_ITEM_LIST, 3000, false
                )
            )


            shopItemList.add(
                ShoppingItemModel(
                    "", "", null,
                    R.drawable.shop_1,
                    SectionType.ALL_SHOP_LIST, null, null
                )
            )
            shopItemList.add(
                ShoppingItemModel(
                    "", "", null,
                    R.drawable.shop_2,
                    SectionType.ALL_SHOP_LIST, null, null
                )
            )
            shopItemList.add(
                ShoppingItemModel(
                    "", "", null,
                    R.drawable.shop_1,
                    SectionType.ALL_SHOP_LIST, null, null
                )
            )
            shopItemList.add(
                ShoppingItemModel(
                    "", "", null,
                    R.drawable.shop_2,
                    SectionType.ALL_SHOP_LIST, null, null
                )
            )

            viewPagerBannerList.add(
                ShoppingItemModel(
                    "", "", "",
                    R.drawable.single_banner_4,
                    SectionType.NONE, null, false
                )
            )
            viewPagerBannerList.add(
                ShoppingItemModel(
                    "", "", "",
                    R.drawable.single_banner_3,
                    SectionType.NONE, null, false
                )
            )
            viewPagerBannerList.add(
                ShoppingItemModel(
                    "", "", "",
                    R.drawable.single_banner_1,
                    SectionType.NONE, null, false
                )
            )


            mainSectionModels.add(
                SectionModels(
                    context.getString(R.string.today_tranding), 1,
                    SectionType.ALL_ITEM_LIST, sellItemList, null, null
                )
            )
            mainSectionModels.add(
                SectionModels(
                    null, 2,
                    SectionType.ONE_BANNER, null,
                    R.drawable.single_banner_1, null
                )
            )
            mainSectionModels.add(
                SectionModels(
                    null, 3,
                    SectionType.ONE_BANNER, null,
                    R.drawable.single_banner_2, null
                )
            )
            mainSectionModels.add(
                SectionModels(
                    null, 4,
                    SectionType.VIEW_PAGER, null, null, viewPagerBannerList
                )
            )
            mainSectionModels.add(
                SectionModels(
                    context.getString(R.string.shop_by_merchant), 5,
                    SectionType.ALL_SHOP_LIST, shopItemList, null, null
                )
            )
            mainSectionModels.add(
                SectionModels(
                    null, 6,
                    SectionType.ONE_BANNER, null,
                    R.drawable.single_banner_3, null
                )
            )

            isFirstTime = false
        }
    }

    fun getCategoryListSearchView() : ArrayList<ShoppingItemModel>{
        var categoryListForSearchView = ArrayList<ShoppingItemModel>()

        categoryListForSearchView.add(
            ShoppingItemModel(
                "", "", "Fashion",
                R.drawable.fashion,
                SectionType.NONE, null, false
            )
        )
        categoryListForSearchView.add(
            ShoppingItemModel(
                "", "", "Electronics",
                R.drawable.electronics,
                SectionType.NONE, null, false
            )
        )
        categoryListForSearchView.add(
            ShoppingItemModel(
                "", "", "Home Garden",
                R.drawable.home_gardens,
                SectionType.NONE, null, false
            )
        )
        categoryListForSearchView.add(
            ShoppingItemModel(
                "", "", "Beauty",
                R.drawable.beauty,
                SectionType.NONE, null, false
            )
        )
        categoryListForSearchView.add(
            ShoppingItemModel(
                "", "", "Toyes",
                R.drawable.toyes,
                SectionType.NONE, null, false
            )
        )
        categoryListForSearchView.add(
            ShoppingItemModel(
                "", "", "Jewelry",
                R.drawable.jewelry,
                SectionType.NONE, null, false
            )
        )
        categoryListForSearchView.add(
            ShoppingItemModel(
                "", "", "Health Care",
                R.drawable.health_care,
                SectionType.NONE, null, false
            )
        )
        categoryListForSearchView.add(
            ShoppingItemModel(
                "", "", "Foods",
                R.drawable.foods,
                SectionType.NONE, null, false
            )
        )

        return categoryListForSearchView;
    }

    fun getSearchHistoryList() : ArrayList<SearchHistoryModel>{
        var searchHistoryModelList = ArrayList<SearchHistoryModel>()
        var recentSearchHistoryModelList = ArrayList<String>()
        var topSearchHistoryModelList = ArrayList<String>()

        recentSearchHistoryModelList.add(context.getString(R.string.recent_1))
        recentSearchHistoryModelList.add(context.getString(R.string.recent_2))
        recentSearchHistoryModelList.add(context.getString(R.string.recent_3))

        topSearchHistoryModelList.add(context.getString(R.string.top_1))
        topSearchHistoryModelList.add(context.getString(R.string.top_2))
        topSearchHistoryModelList.add(context.getString(R.string.top_3))
        topSearchHistoryModelList.add(context.getString(R.string.top_4))

        searchHistoryModelList.add(SearchHistoryModel(context.getString(R.string.recent_searches),recentSearchHistoryModelList))
        searchHistoryModelList.add(SearchHistoryModel(context.getString(R.string.top_searches),topSearchHistoryModelList))

        return searchHistoryModelList;
    }

    fun getSearchResultsList() : ArrayList<ShoppingItemModel>{
        var searchResultList = ArrayList<ShoppingItemModel>()
        searchResultList.add(
            ShoppingItemModel(
                "", "", "Hikkaduwa – Body Spray 200ml",
                R.drawable.search_result_1,
                SectionType.ALL_ITEM_LIST, 2600, false
            )
        )
        searchResultList.add(
            ShoppingItemModel(
                "", "", "Blue Water Lily – Body Spray 200ml",
                R.drawable.search_result_2,
                SectionType.ALL_ITEM_LIST, 5000, true
            )
        )
        searchResultList.add(
            ShoppingItemModel(
                "", "", "Mirissa – Body Spray 200ml",
                R.drawable.search_result_3,
                SectionType.ALL_ITEM_LIST, 3400, false
            )
        )
        searchResultList.add(
            ShoppingItemModel(
                "", "", "Night Jasmine – Body Spray 200ml",
                R.drawable.search_result_4,
                SectionType.ALL_ITEM_LIST, 3200, false
            )
        )

        return searchResultList
    }

}
