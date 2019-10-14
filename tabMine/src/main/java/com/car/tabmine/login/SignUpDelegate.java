package com.car.tabmine.login;

import android.view.View;

import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.mvp.mvpdefault.DefaultContract;
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl;
import com.car.core.mvp.view.BaseMvpFragment;
import com.car.tabmine.R;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.login
 * @time 2019/10/14 20:48
 * @description
 */

@CreatePresenter(DefaultPresenterImpl.class)
public class SignUpDelegate extends BaseMvpFragment<DefaultPresenterImpl>
    implements DefaultContract.IDefaultView {

    @Override
    public Object setLayout() {
        return R.layout.signup_delegate;
    }

    @Override
    public void bindView(View view) {

    }

    @Override
    public void onResult(boolean flag, String result) {

    }
}
