package com.car.tabmall;

import android.view.View;

import com.car.core.delegate.BottomItemDelegate;
import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.mvp.mvpdefault.DefaultContract;
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmall
 * @time 2019/10/7 22:56
 * @description
 */
@CreatePresenter(DefaultPresenterImpl.class)
public class MallDelegate extends BottomItemDelegate<DefaultPresenterImpl>
        implements DefaultContract.IDefaultView {
    @Override
    public Object setLayout() {
        return R.layout.mall_delegate;
    }

    @Override
    public void BindView(View view) {

    }

    @Override
    public void onResult(boolean flag, String result) {

    }
}
