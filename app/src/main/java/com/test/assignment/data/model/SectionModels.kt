package com.test.assignment.data.model

import com.test.assignment.helper.SectionType

data class SectionModels(
    var TITLE: String? = "",
    var ORDER_INDEX: Int?,
    val CELL_TYPE: SectionType?,
    val arrayList: ArrayList<ShoppingItemModel>?,
    val singleImageShoppingItem: Int?,
    val viewAdapterArrayList: ArrayList<ShoppingItemModel>?
) {
}