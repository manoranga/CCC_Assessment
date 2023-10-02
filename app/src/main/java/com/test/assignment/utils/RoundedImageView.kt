package com.test.assignment.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView


class RoundedImageView : AppCompatImageView {
    private var cornerRadius = 0f

    constructor(context: Context?) : super(context!!) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!, attrs, defStyle
    ) {
        init()
    }

    private fun init() {
        cornerRadius = 10f // Set your desired corner radius
    }

    public fun setRadius( cornerRadius : Float) {
        this.cornerRadius = cornerRadius // Set your desired corner radius
    }

    override fun onDraw(canvas: Canvas) {
        val path = Path()
        val width = width
        val height = height
        path.addRoundRect(
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
            cornerRadius,
            cornerRadius,
            Path.Direction.CW
        )
        canvas.clipPath(path)
        super.onDraw(canvas)
    }
}
