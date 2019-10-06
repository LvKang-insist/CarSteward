package com.car.core.delegate;

/**
 * Copyright (C)
 *
 * @file: BottomTabBean
 * @author: 345
 * @Time: 2019/4/25 19:40
 * @description: 导航栏 的Tab
 */
public final class BottomTabBean {
    /**
     * 图标
     */
    private final Integer ICON;
    /**
     * 文字
     */
    private final CharSequence TITLE;

    public BottomTabBean(Integer ICON, CharSequence TITLE) {
        this.ICON = ICON;
        this.TITLE = TITLE;
    }

    public Integer getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }
}
