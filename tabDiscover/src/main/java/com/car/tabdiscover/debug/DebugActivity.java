package com.car.tabdiscover.debug;

import android.annotation.SuppressLint;
import com.car.core.delegate.base.BaseDelegate;
import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl;
import com.car.core.mvp.view.BaseMvpActivity;
import com.car.tabdiscover.DiscoverDelegate;


/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabhome.debug
 * @time 2019/10/8 21:12
 * @description
 */
@SuppressLint("Registered")
@CreatePresenter(DefaultPresenterImpl.class)
public class DebugActivity extends BaseMvpActivity<DefaultPresenterImpl> {

    @Override
    public BaseDelegate setRootDelegate() {
        return new DiscoverDelegate();
    }

    @Override
    public void bindView() {

    }
}
