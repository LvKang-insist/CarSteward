package com.car.tabmine.setting;

import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl;
import com.car.core.mvp.view.BaseMvpFragment;
import com.car.tabmine.R;
import com.car.tabmine.R2;
import com.elvishew.xlog.XLog;
import com.hjq.toast.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import butterknife.BindView;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.setting
 * @time 2019/10/28 20:48
 * @description
 */
@CreatePresenter(DefaultPresenterImpl.class)
public class SettingDelegate extends BaseMvpFragment<DefaultPresenterImpl> {

    @BindView(R2.id.toolbar_title)
    AppCompatTextView mToolbarTitle = null;
    @BindView(R2.id.toolbar)
    Toolbar mToolbar = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_setting;
    }

    @Override
    public void bindView(View view) {
        mToolbarTitle.setText(R.string.setting);
        mToolbarTitle.setTextColor(getResources().getColor(R.color.white));
        mToolbar.setBackgroundColor(getResources().getColor(R.color.red));
        getStatusBarConfig().statusBarColor(R.color.red).init();
    }

    @Override
    public int getToolbar() {
        return R.id.toolbar;
    }
}
