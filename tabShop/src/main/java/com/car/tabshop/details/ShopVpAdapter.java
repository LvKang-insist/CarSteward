package com.car.tabshop.details;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.car.core.mvp.view.BaseMvpDelegate;
import com.car.tabshop.details.tab.ServiceProductTabDelegate;
import com.car.tabshop.details.tab.ShopAppraiseTabDelegate;
import com.car.tabshop.details.tab.ShopDetailsTabDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabshop.details
 * @time 2019/10/29 21:42
 * @description
 */
public class ShopVpAdapter extends FragmentPagerAdapter {

    List<BaseMvpDelegate> list = new ArrayList<>();
    String[] str = new String[]{"服务产品", "商品详情", "商品评价"};

    public ShopVpAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        list.add(new ServiceProductTabDelegate());
        list.add(new ShopDetailsTabDelegate());
        list.add(new ShopAppraiseTabDelegate());
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return str[position];
    }
}
