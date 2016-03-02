package com.o3dr.android.lib.andwidgets.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

import com.o3dr.android.lib.andwidgets.R;

public class VerticalSeekBar extends SeekBar {
    private boolean fromUser = false;
    private boolean onlySlideOnThumb = false;

    private boolean wasDownOnThumb = false;

    public VerticalSeekBar(Context context) {
        super(context);
    }

    public VerticalSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        getAttribute(context, attrs);
    }

    public VerticalSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        getAttribute(context, attrs);
    }

    private void getAttribute(Context context, AttributeSet attrs) {
        TypedArray customAttr = context.getTheme().obtainStyledAttributes(
            attrs,
            R.styleable.VerticalSeekBar,
            0, 0);

        boolean onlyThumb = customAttr.getBoolean(R.styleable.VerticalSeekBar_onlyThumb, false);
        setOnlySlideOnThumb(onlyThumb);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(h, w, oldh, oldw);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    protected void onDraw(Canvas c) {
        c.rotate(-90);
        c.translate(-getHeight(), 0);

        super.onDraw(c);
    }

    @Override
    public void setProgress(int progress) {
        setProgressFromUser(progress, false);
    }

    public void setProgressFromUser(int progress, boolean fromUser) {
        this.fromUser = fromUser;
        super.setProgress(progress);
        onSizeChanged(getWidth(), getHeight(), 0, 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return false;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (onlySlideOnThumb) {
                    if (isOnThumb(event)) {
                        updateProgress(event.getY());
                        wasDownOnThumb = true;
                    } else {
                        return false;
                    }
                } else {
                    wasDownOnThumb = true;
                    updateProgress(event.getY());
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (wasDownOnThumb) {
                    updateProgress(event.getY());
                }
                break;
            case MotionEvent.ACTION_UP:
                wasDownOnThumb = false;
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }

    private void updateProgress(float y) {
        int i = getMax() - (int) (getMax() * y / getHeight());
        setProgressFromUser(i, true);
    }

    private boolean isOnThumb(MotionEvent event) {
        Drawable thumb = getThumb();
        Rect thumbBounds = thumb.getBounds();

        if (inBetween(event.getX(), thumbBounds.top + getX(), thumbBounds.bottom + getX())
            && inBetween(event.getY(), getHeight() - thumbBounds.left, getHeight() - thumbBounds.right)) {
            return true;
        }

        return false;
    }

    private boolean inBetween(float loc, float bound1, float bound2) {
        if (loc >= bound1 && loc <= bound2 || loc <= bound1 && loc >= bound2) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFromUser() {
        return fromUser;
    }

    public boolean isOnlySlideOnThumb() {
        return onlySlideOnThumb;
    }

    public void setOnlySlideOnThumb(boolean onlySlideOnThumb) {
        this.onlySlideOnThumb = onlySlideOnThumb;
        invalidate();
    }
}