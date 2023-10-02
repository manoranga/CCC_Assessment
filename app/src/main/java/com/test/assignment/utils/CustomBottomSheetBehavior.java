package com.test.assignment.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class CustomBottomSheetBehavior<V extends View> extends BottomSheetBehavior<V> {

    private boolean shouldScroll = false;

    public CustomBottomSheetBehavior() {
        super();
    }

    public CustomBottomSheetBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull CoordinatorLayout parent, @NonNull V child, @NonNull MotionEvent event) {
        if (!shouldScroll) {
            return false; // Disable touch interception when scrolling is not allowed
        }
        return super.onInterceptTouchEvent(parent, child, event);
    }

    @Override
    public boolean onTouchEvent(@NonNull CoordinatorLayout parent, @NonNull V child, @NonNull MotionEvent event) {
        if (!shouldScroll) {
            return false; // Disable touch handling when scrolling is not allowed
        }
        return super.onTouchEvent(parent, child, event);
    }

    public void setShouldScroll(boolean shouldScroll) {
        this.shouldScroll = shouldScroll;
    }
}
