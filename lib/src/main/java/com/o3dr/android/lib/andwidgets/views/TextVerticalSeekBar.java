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
    public @interface TextGravity {
    }

    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int CENTER = 2;

    @IntDef({
        THUMB,
        PROGRESS
    })
    public @interface AlignText {
    }

    public static final int THUMB = 0;
    public static final int PROGRESS = 1;

    private static final int[] DEFAULT_ATTRS = new int[]{
        android.R.attr.text,
        android.R.attr.textSize,
        android.R.attr.textColor
    };

    private String text;
    private float textSize = 15;
    private int textColor = Color.WHITE;
    @TextGravity
    private int gravity;
    @AlignText
    private int alignText;

    private TextPaint textPaint;
    private float textPadding;

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

                    TypedArray customAttr = context.getTheme().obtainStyledAttributes(
                        attrs,
                        R.styleable.TextVerticalSeekBar,
                        0, 0);

                    @TextGravity int textGravity = customAttr.getInteger(R.styleable.TextVerticalSeekBar_textGravity, 0);
                    setGravity(textGravity);

                    float textSize = customAttr.getDimension(R.styleable.TextVerticalSeekBar_textSize, Utils.dpToPx(getContext(), 15));
                    setTextSize(textSize);

                    int textColor = customAttr.getColor(R.styleable.TextVerticalSeekBar_textColor, Color.WHITE);
                    setTextColor(textColor);

                    @AlignText int alignText = customAttr.getInteger(R.styleable.TextVerticalSeekBar_alignText, 0);
                    setAlignText(alignText);

                } finally {
                    defAttrs.recycle();
                }
            }
        }

        textPadding = Utils.dpToPx(context, 2);
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

            float xCoord;
            switch (alignText) {
                case THUMB:
                default:
                    xCoord = thumbBounds.exactCenterX() + (textPaint.descent() - textPaint.ascent()/2);
                    break;
                case PROGRESS:
                    xCoord = thumbBounds.left;
                    break;
            }

            float yCoord;
            switch (gravity) {
                case LEFT:
                    yCoord = thumbBounds.top + textPadding;
                    textPaint.setTextAlign(Paint.Align.LEFT);
                    break;
                case RIGHT:
                    yCoord = thumbBounds.bottom - textPadding;
                    textPaint.setTextAlign(Paint.Align.RIGHT);
                    break;
                case CENTER:
                default:
                    textPaint.setTextAlign(Paint.Align.CENTER);
                    yCoord = thumbBounds.exactCenterY();
                    break;
            }

            canvas.save();
            canvas.rotate(90, xCoord, yCoord);

            canvas.drawText(text, xCoord, yCoord, textPaint);
            canvas.restore();
        }
    }

    public void setText(String text) {
        this.text = text;

        invalidate();
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        textPaint.setTextSize(textSize);

        invalidate();
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        textPaint.setColor(textColor);

        invalidate();
    }

    public void setGravity(@TextGravity int gravity) {
        this.gravity = gravity;

        invalidate();
    }


    public void setAlignText(@AlignText int alignText) {
        this.alignText = alignText;
        invalidate();
    }

    public String getText() {
        return text;
    }

    public float getTextSize() {
        return textSize;
    }

    public int getTextColor() {
        return textColor;
    }

    @TextGravity
    public int getGravity() {
        return gravity;
    }

    @AlignText
    public int getAlignText() {
        return alignText;
    }
}