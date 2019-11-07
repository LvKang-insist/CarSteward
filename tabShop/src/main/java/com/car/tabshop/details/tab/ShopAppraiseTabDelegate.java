package com.car.tabshop.details.tab;

import android.graphics.Color;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl;
import com.car.core.mvp.view.BaseMvpDelegate;
import com.car.tabshop.R;
import com.car.tabshop.R2;

import butterknife.BindView;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabshop.details
 * @time 2019/10/29 20:43
 * @description 门店评价
 */
@CreatePresenter(DefaultPresenterImpl.class)
public class ShopAppraiseTabDelegate extends BaseMvpDelegate<DefaultPresenterImpl> {

    @BindView(R2.id.toolbar_title)
    AppCompatTextView mToolbarTitle = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_details_tab;
    }

    @Override
    public int getToolbar() {
        return R.id.toolbar;
    }

    @Override
    public void bindView(View view) {
        mToolbarTitle.setText("门店详情");
        mToolbarTitle.setTextColor(Color.RED);
    }


}
