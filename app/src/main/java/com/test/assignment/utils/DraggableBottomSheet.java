package com.test.assignment.utils;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DraggableBottomSheet extends FrameLayout {

    private float lastY;
    //    private DraggableBottomSheetBinding binding;
    private Context context;
    private int contentHeight;
    private boolean isShow = false;

    public DraggableBottomSheet(Context context) {
        super(context);
        init(context);
    }

    public DraggableBottomSheet(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DraggableBottomSheet(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public DraggableBottomSheet(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        setupTouchEvents();
    }

    public void showSheet(boolean show, int viewHeight) {
        if (show) {
            if (getVisibility() == GONE) {
                setVisibility(VISIBLE);
                setY(viewHeight);
                isShow = false;
            }

            contentHeight = viewHeight * 2 / 3;

            // Resize the child view to the new height
            View childView = getChildAt(0);
            ViewGroup.LayoutParams layoutParams = childView.getLayoutParams();
            layoutParams.height = contentHeight;
            childView.setLayoutParams(layoutParams);

            if (!isShow) {
                animateY(viewHeight - contentHeight);
                isShow = true;
            }
        } else {
            if (isShow) {
                animateY(viewHeight);
                isShow = false;
            }
        }
    }

    public void showSheetFullView(boolean show, int viewHeight) {
        if (show) {
            if (getVisibility() == GONE) {
                setVisibility(VISIBLE);
            }
            setY(viewHeight);
            isShow = false;


            contentHeight = viewHeight;

            // Resize the child view to the new height
            View childView = getChildAt(0);
            ViewGroup.LayoutParams layoutParams = childView.getLayoutParams();
            layoutParams.height = contentHeight;
            childView.setLayoutParams(layoutParams);

            if (!isShow) {
                animateY(0);
                setY(0);
                isShow = true;
            }
        } else {
            if (isShow) {
                animateY(viewHeight);
                isShow = false;
            }
        }
    }

    private void animateY(int targetY) {
        ValueAnimator animator = ObjectAnimator.ofFloat(this, "y", getY(), targetY);
        animator.setDuration(500); // Set the duration of the animation in milliseconds
        animator.start();
    }

    public void animateHeight(int targetHeight) {
        ValueAnimator animator = ValueAnimator.ofInt(contentHeight, targetHeight);
        animator.setDuration(300);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                contentHeight = (int) animation.getAnimatedValue();
                requestLayout();
            }
        });
        animator.start();
    }

    private void setupTouchEvents() {
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        float deltaY = event.getRawY() - lastY;
                        lastY = event.getRawY();

                        // Calculate the new top position of the view
                        int top = (int) (getY() + deltaY);

                        // Restrict the top position within the parent view bounds
                        int parentHeight = ((View) getParent()).getHeight();
                        int minHeight = getHeight(); // Set a minimum height if needed
                        int maxHeight = parentHeight; // Set a maximum height if needed
                        top = Math.max(Math.min(top, maxHeight), parentHeight - minHeight);
                        top = Math.min(top, (parentHeight * 2 / 3));

                        contentHeight = parentHeight - top;
                        // Update the view's position
                        setY(top);

                        System.out.println("DRAGGABLE_TAG   parentHeight  " + parentHeight);
                        System.out.println("DRAGGABLE_TAG   top  " + top);
                        System.out.println("DRAGGABLE_TAG   contentHeight  " + contentHeight);

                        // Resize the child view to the new height
                        View childView = getChildAt(0);
                        ViewGroup.LayoutParams layoutParams = childView.getLayoutParams();
                        layoutParams.height = parentHeight - top;
                        childView.setLayoutParams(layoutParams);

                        return true;
                }

                return true;
            }
        });
    }
}
