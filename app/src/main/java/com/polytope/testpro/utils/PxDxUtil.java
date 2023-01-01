package com.polytope.testpro.utils;

import android.content.Context;

/**
 * @author: 雪豹高科
 * @time: 2020-12-9
 */

public class PxDxUtil {
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
