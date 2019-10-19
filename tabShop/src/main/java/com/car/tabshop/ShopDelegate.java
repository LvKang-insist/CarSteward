package com.car.tabshop;

import android.view.View;

import com.car.core.delegate.BottomItemDelegate;
import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.mvp.mvpdefault.DefaultContract;
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabshop
 * @time 2019/10/7 22:59
 * @description
 */
@CreatePresenter(DefaultPresenterImpl.class)
public class ShopDelegate extends BottomItemDelegate<DefaultPresenterImpl>
        implements DefaultContract.IDefaultView {
    @Override
    public Object setLayout() {
        return R.layout.shop_delegate;
    }

    @Override
    public void bindView(View view) {

    }

    @Override
    public void onResult(String result) {

    }
}
