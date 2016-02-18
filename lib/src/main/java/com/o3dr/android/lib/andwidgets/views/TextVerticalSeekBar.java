package com.o3dr.android.lib.andwidgets.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntDef;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.o3dr.android.lib.andwidgets.R;
import com.o3dr.android.lib.andwidgets.views.util.Utils;

/**
 * Created by chavi on 2/17/16.
 */
public class TextVerticalSeekBar extends VerticalSeekBar {
    @IntDef({
        LEFT,
        RIGHT,
        CENTER
    })
    public @interface TextGravity{}
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int CENTER = 2;

    private static final int[] DEFAULT_ATTRS = new int[]{
        android.R.attr.text,
        android.R.attr.textSize,
        android.R.attr.textColor
    };

    private String text;
    private int textSize = 15;
    private int textColor = Color.WHITE;
    @TextGravity
    private int gravity;

    private TextPaint textPaint;

    public TextVerticalSeekBar(Context context) {
        super(context);

        createTextPaint(context, null);
    }

    public TextVerticalSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        createTextPaint(context, attrs);
    }

    public TextVerticalSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        createTextPaint(context, attrs);
    }

    private void createTextPaint(Context context, AttributeSet attrs) {
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setStyle(Paint.Style.FILL);

        if (attrs != null) {
            TypedArray defAttrs = context.obtainStyledAttributes(attrs, DEFAULT_ATTRS);

            if (defAttrs != null) {
                try {
                    String text = defAttrs.getString(0);
                    setText(text);

                    int textSize = defAttrs.getInt(1, 15);
                    setTextSize(textSize);

                    int textColor = defAttrs.getInt(2, Color.WHITE);
                    setTextColor(textColor);

                    TypedArray customAttr = context.getTheme().obtainStyledAttributes(
                        attrs,
                        R.styleable.TextVerticalSeekBar,
                        0, 0);

                    @TextGravity int textGravity = customAttr.getInteger(R.styleable.TextVerticalSeekBar_textGravity, 0);
                    setGravity(textGravity);

                } finally {
                    defAttrs.recycle();
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas c) {
        super.onDraw(c);
        drawTextOnThumb(c);
    }

    private void drawTextOnThumb(Canvas canvas) {
        if (!TextUtils.isEmpty(text)) {
            Drawable thumb = getThumb();
            Rect thumbBounds = thumb.getBounds();
            int x = (int) (thumbBounds.centerX() + (thumb.getIntrinsicWidth()/2) + getThumbOffset() +  ((textPaint.descent() + textPaint.ascent()) / 2));
            int y = thumbBounds.centerY();

            canvas.save();
            canvas.rotate(90, x, y);
            canvas.drawText(text, x, y, textPaint);
            canvas.restore();
        }
    }

    public void setText(String text) {
        this.text = text;

        invalidate();
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
        textPaint.setTextSize(Utils.dpToPx(getContext(), textSize));

        invalidate();
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        textPaint.setColor(textColor);

        invalidate();
    }

    public void setGravity(@TextGravity int gravity) {
        this.gravity = gravity;

        Paint.Align align;
        switch (gravity) {
            case CENTER:
                align = Paint.Align.CENTER;
                break;
            case RIGHT:
                align = Paint.Align.RIGHT;
                break;
            case LEFT:
            default:
                align = Paint.Align.LEFT;
                break;
        }
        textPaint.setTextAlign(align);

        invalidate();
    }

    public String getText() {
        return text;
    }

    public int getTextSize() {
        return textSize;
    }

    public int getTextColor() {
        return textColor;
    }

    @TextGravity
    public int getGravity() {
        return gravity;
    }
}
