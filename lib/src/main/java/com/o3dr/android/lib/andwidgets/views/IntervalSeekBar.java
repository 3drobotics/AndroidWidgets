package com.o3dr.android.lib.andwidgets.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;

import com.o3dr.android.lib.andwidgets.R;
import com.o3dr.android.lib.andwidgets.views.util.Utils;

/**
 * SeekBar with dots at specified intervals
 */
public class IntervalSeekBar extends SeekBar {
    private int[] dots;
    private float dotRadius;
    private Paint paint;
    private int dotColor;

    public IntervalSeekBar(Context context) {
        super(context);
        init(Color.WHITE);
        setDotRadius(Utils.dpToPx(getContext(), 6));
    }

    public IntervalSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public IntervalSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray customAttrs = context. getTheme().obtainStyledAttributes(
            attrs,
            R.styleable.IntervalSeekBar,
            defStyleAttr,
            0);

        int dotColor = customAttrs.getColor(R.styleable.IntervalSeekBar_dotColor, Color.WHITE);
        init(dotColor);

        float dotRadius = customAttrs.getDimension(R.styleable.IntervalSeekBar_dotRadius,
            Utils.dpToPx(getContext(), 6));
        setDotRadius(dotRadius);
    }

    private void init(int dotColor) {
        paint = new Paint();
        setDotColor(dotColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int h = getHeight();

        int startOffset = getPaddingLeft();
        int endOffset = getPaddingRight();
        float w = getWidth() - (startOffset + endOffset);

        if ((w == 0) || (h == 0)) {
            return;
        }

        float ratio = w / (float) getMax();
        if (dots != null) {
            for (int i = 0; i < dots.length; i++) {
                float dotLoc = ratio * dots[i];
                canvas.drawCircle((dotLoc + startOffset), h / 2, dotRadius, paint);
            }
        }

        // redraw just the thumb so the progress dot is drawn over the other dots
        drawThumb(canvas);
    }

    /**
     * This is a duplicate of the drawThumb method in {@link android.widget.AbsSeekBar} which is
     * package-private so it can't be called from this class.
     *
     * @param canvas
     */
    void drawThumb(Canvas canvas) {
        Drawable thumb = getThumb();
        if (thumb != null) {
            canvas.save();
            // Translate the padding. For the x, we need to allow the thumb to
            // draw in its extra space
            canvas.translate(getPaddingLeft() - getThumbOffset(), getPaddingTop());
            thumb.draw(canvas);
            canvas.restore();
        }
    }

    public void setDotLocations(int[] dots) {
        this.dots = dots;
        invalidate();
    }

    public int[] getDotLocations() {
        return dots;
    }

    public int getDotColor() {
        return dotColor;
    }

    public void setDotColor(int dotColor) {
        this.dotColor = dotColor;
        paint.setColor(dotColor);
        invalidate();
    }

    public float getDotRadius() {
        return dotRadius;
    }

    public void setDotRadius(float dotRadius) {
        this.dotRadius = dotRadius;
        invalidate();
    }

}
