package com.car.core.utils.dimen;

import com.car.core.latte.Latte;


/**
 * Copyright (C)
 *
 * @file: StatusBarHeight
 * @author: 345
 * @Time: 2019/4/30 11:39
 * @description: 获取 状态栏的高度 和转换
 */
public class StatusBarHeight {

    /**
     * @return 返回 状态栏的 高度，以像素为单位
     */
    public static int getStaticBarHeight(){
        int result = 0;
        int resourceId = Latte.getAppContext().getResources().getIdentifier("status_bar_height",
                "dimen","android");
        if (resourceId > 0){
            result = Latte.getAppContext().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     *  根据手机的分辨率 从 px(像素)的单位 转换为 dp
     */
    public static int px2dip(float pxValue){
        final float scale = Latte.getAppContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue/scale+0.5f);
    }

    /**
     * 根据手机的分辨路 从 dp单位 转换为 px(像素)
     */
    private static int dip2px(float dipValue){
        final float scale = Latte.getAppContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue*scale+0.5f);
    }
}
