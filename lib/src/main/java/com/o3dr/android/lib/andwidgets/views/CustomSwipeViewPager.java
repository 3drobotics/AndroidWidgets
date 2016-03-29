package com.o3dr.android.lib.andwidgets.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.o3dr.android.lib.andwidgets.R;

/**
 * A custom view pager that allows enabling and disabling of swipe.
 */
public class CustomSwipeViewPager extends ViewPager {
    private boolean isSwipeEnabled = true;

    public CustomSwipeViewPager(Context context) {
        super(context);
    }

    public CustomSwipeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray customAttrs = context.getTheme().obtainStyledAttributes(
            attrs,
            R.styleable.CustomSwipeViewPager,
            0, 0);

        boolean swipeEnabled = customAttrs.getBoolean(R.styleable.CustomSwipeViewPager_swipeEnabled, true);
        setIsSwipeEnabled(swipeEnabled);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.isSwipeEnabled && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.isSwipeEnabled && super.onInterceptTouchEvent(event);
    }

    public boolean isSwipeEnabled() {
        return isSwipeEnabled;
    }

    /**
     * Enable/Disable the swipe ability of the view pager
     * @param isSwipeEnabled Whether swipe should be enabled or disabled
     */
    public void setIsSwipeEnabled(boolean isSwipeEnabled) {
        this.isSwipeEnabled = isSwipeEnabled;
        invalidate();
    }
}