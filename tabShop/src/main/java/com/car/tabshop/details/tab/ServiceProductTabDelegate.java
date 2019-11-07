package com.car.tabshop.details.tab;

import android.view.View;

import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl;
import com.car.core.mvp.view.BaseMvpDelegate;
import com.car.tabshop.R;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabshop.details.tab
 * @time 2019/10/29 21:35
 * @description 服务产品
 */
@CreatePresenter(DefaultPresenterImpl.class)
public class ServiceProductTabDelegate extends BaseMvpDelegate<DefaultPresenterImpl> {
    @Override
    public Object setLayout() {
        return R.layout.delegate_service_product_tab;
    }

    @Override
    public void bindView(View view) {

    }
}
