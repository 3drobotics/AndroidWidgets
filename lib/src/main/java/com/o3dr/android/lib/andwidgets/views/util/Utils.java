package com.o3dr.android.lib.andwidgets.views.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by Fredia Huya-Kouadio on 3/9/15.
 */
public class Utils {
    public static float dpToPx(Context context, int dp) {
        Resources r = context.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());

        return px;

    }
}
