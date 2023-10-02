package com.test.assignment.utils

import android.content.Context
import android.util.AttributeSet
import androidx.core.widget.NestedScrollView


class CustomNestedScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : NestedScrollView(context, attrs, defStyleAttr) {

    private var nestedScrollListener: NestedScrollListener? = null

    fun setNestedScrollListener(listener: NestedScrollListener) {
        this.nestedScrollListener = listener
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)

        nestedScrollListener?.onScroll(t )
    }

    interface NestedScrollListener {
        fun onScroll(dy: Int)
    }
}


