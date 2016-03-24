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
 * VerticalSeek bar with text written on the thumb or end of seek bar.
 */
public class TextVerticalSeekBar extends VerticalSeekBar {
    private int thumbOffset;

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

    //Sorted integer order
    private static final int[] DEFAULT_ATTRS = new int[]{
        android.R.attr.textSize, //0
        android.R.attr.textColor, //1
        android.R.attr.thumbOffset, //2
        android.R.attr.text //3
    };

    private String text;
    private float textSize = 15;
    private int textColor = Color.WHITE;
    @TextGravity
    private int textGravity;
    @AlignText
    private int alignText;

    private TextPaint textPaint;
    private float textPadding;

    private Drawable disabledThumbDrawable;

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
                    float textSize = defAttrs.getDimension(0, Utils.dpToPx(getContext(), 15));
                    setTextSize(textSize);

                    int textColor = defAttrs.getColor(1, Color.WHITE);
                    setTextColor(textColor);

                    thumbOffset = defAttrs.getDimensionPixelOffset(2, 0);

                    String text = defAttrs.getString(3);
                    setText(text);
                } finally {
                    defAttrs.recycle();
                }
            }

            TypedArray customAttr = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.TextVerticalSeekBar,
                0, 0);

            @TextGravity int textGravity = customAttr.getInteger(R.styleable.TextVerticalSeekBar_textGravity, LEFT);
            setTextGravity(textGravity);

            @AlignText int alignText = customAttr.getInteger(R.styleable.TextVerticalSeekBar_alignText, THUMB);
            setAlignText(alignText);

            Drawable disabledThumbDrawableId = customAttr.getDrawable(R.styleable.TextVerticalSeekBar_disabledThumb);
            setDisabledThumbDrawable(disabledThumbDrawableId);
        }

        textPadding = Utils.dpToPx(context, 2);
    }

    @Override
    protected void onDraw(Canvas c) {
        super.onDraw(c);
        drawTextOnThumb(c);
    }

    /**
     * Sets whether the seekbar is enabled or disabled. This will also set the seekbar's thumb
     * to disabledThumbDrawable if the the seekbar is disabled. It will also remove any text that
     * was previously on the thumb if disabled and re-add the last text if enabled
     *
     * @param enabled
     */
    @Override
    public void setEnabled(boolean enabled) {
        boolean wasEnabled = isEnabled();
        super.setEnabled(enabled);

        if (wasEnabled == enabled) {
            return;
        }

        if (enabled) {
            if (initialCustomThumb != null) {
                setCustomThumb(initialCustomThumb);
            }
        } else {
            if (disabledThumbDrawable != null) {
                setCustomThumb(disabledThumbDrawable);
            }
        }

        setThumbOffset(thumbOffset);
        setProgress(getProgress());
    }

    private void drawTextOnThumb(Canvas canvas) {
        String localText = text;
        if (!isEnabled()) {
            localText = "";
        } else if (TextUtils.isEmpty(localText)) {
            localText = "";
        }

        Drawable thumb = getCustomThumb();
        Rect thumbBounds = thumb.getBounds();

        float xCoord;
        switch (alignText) {
            case THUMB:
            default:
                xCoord = thumbBounds.exactCenterX() + ((textPaint.descent() + textPaint.ascent() / 2));
                break;
            case PROGRESS:
                xCoord = thumbBounds.left;
                break;
        }

        float yCoord;
        switch (textGravity) {
            case LEFT:
                yCoord = thumbBounds.top + textPadding;
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
        canvas.drawText(localText, xCoord, yCoord, textPaint);
        canvas.restore();
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

    public void setTextGravity(@TextGravity int gravity) {
        this.textGravity = gravity;

        invalidate();
    }


    public void setAlignText(@AlignText int alignText) {
        this.alignText = alignText;
        invalidate();
    }

    public void setDisabledThumbDrawable(Drawable disabledThumbDrawable) {
        this.disabledThumbDrawable = disabledThumbDrawable;
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
    public int getTextGravity() {
        return textGravity;
    }

    @AlignText
    public int getAlignText() {
        return alignText;
    }

    public Drawable getDisabledDrawableThumb() {
        return disabledThumbDrawable;
    }
}