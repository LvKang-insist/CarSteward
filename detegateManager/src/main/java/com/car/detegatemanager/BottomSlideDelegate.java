package com.car.detegatemanager;


import android.graphics.Color;

import com.car.core.delegate.BottomItemDelegate;
import com.car.core.delegate.BottomTabBean;
import com.car.core.delegate.ItemBuilder;
import com.car.core.delegate.TabItemSlideDelegate;
import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl;
import java.util.LinkedHashMap;

/**
 * @author 345 QQ:1831712732
 * @name MvpFrame
 * @class name：com.latte.core.delegate
 * @time 2019/9/25 21:52
 * @description
 */
@CreatePresenter(DefaultPresenterImpl.class)
public class BottomSlideDelegate extends TabItemSlideDelegate {

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {

        return builder.build();
    }

    @Override
    public int startDelegate() {
        return 0;
    }

    @Override
    public int selectColor() {
        return Color.parseColor("#03FF00");
    }
}
