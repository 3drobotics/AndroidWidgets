package com.o3dr.android.lib.andwidgets.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.o3dr.android.lib.andwidgets.R;

/**
 * A recycler view that will automatically create the number of columns based on specified column
 * width.
 */
public class AutofitRecyclerView extends RecyclerView {
    private static final int defaultSpanCount = 3;

    private float columnWidth = 0;

    private GridLayoutManager manager = null;

    public AutofitRecyclerView(Context context) {
        super(context);
        init();
    }

    public AutofitRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public AutofitRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        init();

        TypedArray customAttrs = context.getTheme().obtainStyledAttributes(
            attrs,
            R.styleable.AutofitRecyclerView,
            defStyle,
            0);

        float columnWidth = customAttrs.getDimension(R.styleable.AutofitRecyclerView_columnWidth, 0);
        setColumnWidth(columnWidth);
    }

    private void init() {
        manager = new GridLayoutManager(getContext(), defaultSpanCount);
        setLayoutManager(manager);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        if (columnWidth > 0) {
            int spanCount = Math.max(1, (int)(getMeasuredWidth() / columnWidth));
            manager.setSpanCount(spanCount);
        }
    }

    public void setColumnWidth(float columnWidth) {
        this.columnWidth = columnWidth;
        invalidate();
    }

    public float getColumnWidth() {
        return columnWidth;
    }
}
