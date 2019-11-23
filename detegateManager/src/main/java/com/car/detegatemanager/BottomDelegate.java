package com.car.detegatemanager;


import android.graphics.Color;

import com.car.core.delegate.BottomItemDelegate;
import com.car.core.delegate.BottomTabBean;
import com.car.core.delegate.ItemBuilder;
import com.car.core.delegate.TabItemDelegate;
import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl;
import com.car.tabdiscover.DiscoverDelegate;
import com.car.tabhome.HomeDelegate;
import com.car.tabmall.MallDelegate;
import com.car.tabmine.MineDelegate;
import com.car.tabshop.ShopDelegate;

import java.util.LinkedHashMap;

/**
 * @author 345 QQ:1831712732
 * @name MvpFrame
 * @class name：com.latte.core.delegate
 * @time 2019/9/25 21:52
 * @description
 */
@CreatePresenter(DefaultPresenterImpl.class)
public class BottomDelegate extends TabItemDelegate {

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        builder.addItem(new BottomTabBean(R.drawable.fragmentation_help,"首页"),new HomeDelegate());
        builder.addItem(new BottomTabBean(R.drawable.fragmentation_help,"门店"),new ShopDelegate());
        builder.addItem(new BottomTabBean(R.drawable.fragmentation_help,"发现"),new DiscoverDelegate());
        builder.addItem(new BottomTabBean(R.drawable.fragmentation_help,"商城"),new MallDelegate());
        builder.addItem(new BottomTabBean(R.drawable.fragmentation_help,"我的"),new MineDelegate());
        return builder.build();
    }

    @Override
    public int startDelegate() {
        return 1;
    }

    @Override
    public int selectColor() {
        return Color.parseColor("#03FF00");
    }
}
