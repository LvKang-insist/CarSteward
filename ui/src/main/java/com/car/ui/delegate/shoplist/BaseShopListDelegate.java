package com.car.ui.delegate.shoplist;

import android.graphics.Color;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.mvp.mvpdefault.DefaultContract;
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl;
import com.car.core.mvp.presenter.IBasePresenter;
import com.car.core.mvp.view.BaseMvpDelegate;
import com.car.ui.R;
import com.car.ui.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.ui.delegate
 * @time 2019/11/26 21:58
 * @description
 */
@CreatePresenter(DefaultPresenterImpl.class)
public class BaseShopListDelegate extends BaseMvpDelegate<DefaultPresenterImpl>
        implements DefaultContract.IDefaultView {

    @BindView(R2.id.base_delegate_shop_select_city_tv)
    AppCompatTextView mCityTv = null;
    @BindView(R2.id.base_delegate_shop_select_city_line)
    View mCityLine = null;
    @BindView(R2.id.base_delegate_shop_select_service_line)
    View mServiceLine = null;
    @BindView(R2.id.base_delegate_shop_select_sort_line)
    View mSortLine = null;
    @BindView(R2.id.base_delegate_shop_select_filtrate_line)
    View mFiltrateLine = null;

    @OnClick({R2.id.base_delegate_shop_select_city,
            R2.id.base_delegate_shop_select_service,
            R2.id.base_delegate_shop_select_sort,
            R2.id.base_delegate_shop_select_filtrate})
    void selectClick(View view) {
        final int id = view.getId();
        defaultSetting();
        if (id == OnSelectType.TYPE_CITY_ID) {
            mCityLine.setVisibility(View.VISIBLE);
        } else if (id == OnSelectType.TYPE_SERVICE_ID) {
            mServiceLine.setVisibility(View.VISIBLE);
        } else if (id == OnSelectType.TYPE_SORT_ID) {
            mSortLine.setVisibility(View.VISIBLE);
        } else {
            mFiltrateLine.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.base_delegate_shop_list;
    }

    @Override
    public void bindView(View view) {
        setToolbarStyle(Color.WHITE, "店铺详情");
    }

    @Override
    public int getToolbar() {
        return R.id.toolbar;
    }

    private void defaultSetting() {
        mCityLine.setVisibility(View.INVISIBLE);
        mServiceLine.setVisibility(View.INVISIBLE);
        mSortLine.setVisibility(View.INVISIBLE);
        mFiltrateLine.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onResult(String result) {

    }
}
