package com.car.tabdiscover;

import android.view.View;

import com.car.core.delegate.BottomItemDelegate;
import com.car.core.delegate.base.BaseDelegate;
import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.mvp.mvpdefault.DefaultContract;
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl;
import com.car.core.mvp.view.BaseMvpActivity;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabdiscover
 * @time 2019/10/7 22:49
 * @description
 */
@CreatePresenter(DefaultPresenterImpl.class)
public class DiscoverDelegate extends BottomItemDelegate<DefaultPresenterImpl>
        implements DefaultContract.IDefaultView {

    @Override
    public Object setLayout() {
        return R.layout.discover_delegate;
    }

    @Override
    public void bindView(View view) {

    }

    @Override
    public void onResult(boolean flag, String result) {

    }
}
