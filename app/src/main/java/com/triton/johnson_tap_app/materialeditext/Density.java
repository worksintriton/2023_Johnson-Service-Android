package com.triton.johnson_tap_app.materialeditext;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

class Density {
     static int dp2px(Context context, float dp) {
        Resources r = context.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
        return Math.round(px);
    }
}
