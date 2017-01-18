package cn.asheng.rotateMenu.utils;

import android.content.Context;

/**
 * Created by Administrator on 2016/12/20.
 */

public class ViewUtils {
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
