package com.test.assignment.data.model

import com.test.assignment.helper.SectionType

data class ShoppingItemModel(
    var CONTENT_NAME: String? = "",
    val ID: String? = "",
    val CONTENT_DESCRIPTION: String? = "",
    val CONTENT_THUMB: Int?,
    val CELL_TYPE: SectionType?,
    val PRICE: Int?,
    val IS_FAV: Boolean?
)