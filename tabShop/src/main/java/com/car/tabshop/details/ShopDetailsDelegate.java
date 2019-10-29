package com.car.tabshop.details;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;


import com.car.tabshop.R2;
import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl;
import com.car.core.mvp.view.BaseMvpFragment;
import com.car.tabshop.R;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabshop.details
 * @time 2019/10/29 20:43
 * @description
 */
@CreatePresenter(DefaultPresenterImpl.class)
public class ShopDetailsDelegate extends BaseMvpFragment<DefaultPresenterImpl> {

    @BindView(R2.id.toolbar_title)
    AppCompatTextView mToolbarTitle = null;
    @BindView(R2.id.delegate_shop_details_vp)
    ViewPager mViewPager = null;
    @BindView(R2.id.delegate_shop_details_tab)
    TabLayout mTabLayout = null;
    @BindView(R2.id.toolbar)
    Toolbar mToolbar = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_details;
    }

    @Override
    public int getToolbar() {
        return R.id.toolbar;
    }

    @Override
    public void bindView(View view) {
        mToolbarTitle.setText("门店详情");
        mToolbarTitle.setTextColor(Color.RED);

        initTabLayout();
        mViewPager.setAdapter(new ShopVpAdapter(getFragmentManager(), 0));
    }

    private void initTabLayout() {
        //tab 是平均分开的
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        //地下 线的颜色
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.red));
        //字的颜色
        mTabLayout.setTabTextColors(ColorStateList.valueOf(Color.BLACK));
        //背景色
        mTabLayout.setBackgroundColor(Color.WHITE);
        //设置ViewPager
        mTabLayout.setupWithViewPager(mViewPager);
    }

}
