package com.test.assignment.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.appcompat.widget.AppCompatTextView;

import com.test.assignment.R;


public class AutoResizeTextView extends AppCompatTextView {
    private float minimumFontSize = 15f;
    private float maximumFontSize = 45f;

    public AutoResizeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(attrs);
    }

    public AutoResizeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(attrs);
    }

    private void initialize(AttributeSet attrs) {
        TypedArray typedArray = null;
        try {
            typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.AutoResizeTextView);

            minimumFontSize = typedArray.getDimension(
                    R.styleable.AutoResizeTextView_minFontSize, 15f);
            maximumFontSize = typedArray.getDimension(
                    R.styleable.AutoResizeTextView_maxFontSize, 45f);

            setSingleLine();
            setEllipsize(TextUtils.TruncateAt.END);
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();

        // Calculate desired font size based on width
        float desiredFontSize = calculateDesiredFontSize(width) - 2;

        // Set the font size
        setTextSize(TypedValue.COMPLEX_UNIT_PX, desiredFontSize);

        // Measure the text again with the new font size
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private float calculateDesiredFontSize(int viewWidth) {
        TextPaint textPaint = getPaint();

        // Get the current text size
        float currentFontSize = getTextSize();

        // Get the text bounds
        Rect textBounds = new Rect();
        textPaint.getTextBounds(getText().toString(), 0, length(), textBounds);

        int textWidth = textBounds.width();

        // Calculate the desired font size
        float desiredFontSize = currentFontSize * viewWidth / textWidth;

        // Apply maximum and minimum font size limits
        float maxFontSize = maximumFontSize; // Get the maximum font size
        float minFontSize = minimumFontSize; // Get the minimum font size
        desiredFontSize = Math.max(Math.min(desiredFontSize, maxFontSize), minFontSize);

        return desiredFontSize;
    }
}
