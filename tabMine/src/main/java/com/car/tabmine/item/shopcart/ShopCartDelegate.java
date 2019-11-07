package com.car.tabmine.item.shopcart;

import android.view.View;

import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.mvp.mvpdefault.DefaultContract;
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl;
import com.car.core.mvp.view.BaseMvpDelegate;
import com.car.tabmine.R;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.item.shopcart
 * @time 2019/11/7 23:18
 * @description
 */
@CreatePresenter(DefaultPresenterImpl.class)
public class ShopCartDelegate extends BaseMvpDelegate<DefaultPresenterImpl> {

    public static ShopCartDelegate create() {
        return new ShopCartDelegate();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void bindView(View view) {

    }
}
