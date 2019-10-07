package com.car.carsteward.example;


import android.annotation.SuppressLint;

import com.car.core.delegate.base.BaseDelegate;
import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl;
import com.car.core.mvp.view.BaseMvpActivity;
import com.car.detegatemanager.BottomDelegate;
import com.car.detegatemanager.BottomSlideDelegate;

/**
 * @author 345 QQ:1831712732
 * @name MvpFrame
 * @class name：com.latte.mvpApp.example
 * @time 2019/8/30 17:12
 * @description 主Activity
 */
@SuppressLint("Registered")
@CreatePresenter(DefaultPresenterImpl.class)
public class ExampleActivity extends BaseMvpActivity {

    @Override
    public BaseDelegate setRootDelegate() {
        return new BottomDelegate();
    }

    @Override
    public void BindView() {

    }
}
