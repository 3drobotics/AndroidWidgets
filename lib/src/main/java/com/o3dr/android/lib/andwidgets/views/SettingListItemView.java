package com.o3dr.android.lib.andwidgets.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.o3dr.android.lib.andwidgets.R;

/**
 * Created by Fredia Huya-Kouadio on 11/16/15.
 */
public class SettingListItemView extends FrameLayout {

    private final View contentView;
    private final TextView headerView;
    private final TextView subHeaderView;

    public SettingListItemView(Context context) {
        this(context, null);
    }

    public SettingListItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SettingListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SettingListItemView, 0, 0);

        try{
            //Retrieve the view attributes
            CharSequence header = a.getText(R.styleable.SettingListItemView_header);
            CharSequence subHeader = a.getText(R.styleable.SettingListItemView_subHeader);
            Drawable headerDrawable = a.getDrawable(R.styleable.SettingListItemView_headerDrawable);

            //Setup the content view
            LayoutInflater inflater = LayoutInflater.from(context);

            contentView = inflater.inflate(R.layout.setting_list_item_view, this, false);
            addView(contentView);

            headerView = (TextView) contentView.findViewById(R.id.list_item_header);
            headerView.setText(header);
            headerView.setCompoundDrawablesWithIntrinsicBounds(headerDrawable, null, null, null);

            subHeaderView = (TextView) contentView.findViewById(R.id.list_item_subheader);
            subHeaderView.setText(subHeader);

        }finally{
            a.recycle();
        }
    }


}
