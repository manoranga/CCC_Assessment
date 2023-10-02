package com.test.assignment.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.recyclerview.widget.RecyclerView;

public class HorizontalRecyclerView extends RecyclerView {
    private float startX;
    private float startY;
    private boolean isScrollingHorizontally;

    public HorizontalRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                startY = ev.getY();
                isScrollingHorizontally = false;
                // Allow parent to intercept touch events
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = Math.abs(ev.getX() - startX);
                float dy = Math.abs(ev.getY() - startY);
                // Only allow horizontal scrolling if the movement is primarily in the horizontal direction
                if (!isScrollingHorizontally && dx > dy) {
                    isScrollingHorizontally = true;
                    // Disallow parent to intercept touch events
                    getParent().requestDisallowInterceptTouchEvent(true);
                } else if (isScrollingHorizontally && dy > dx) {
                    // Allow parent to intercept touch events for vertical scrolling
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                isScrollingHorizontally = false;
                // Allow parent to intercept touch events
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
