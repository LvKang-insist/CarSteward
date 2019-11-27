package com.car.ui.delegate.shoplist;

import android.graphics.Color;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.mvp.mvpdefault.DefaultContract;
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl;
import com.car.core.mvp.presenter.IBasePresenter;
import com.car.core.mvp.view.BaseMvpDelegate;
import com.car.core.utils.bean.BusinessScopeAndShopListBean;
import com.car.core.utils.storage.CarPreference;
import com.car.core.utils.util.BusinessScope;
import com.car.core.utils.util.RequestParam;
import com.car.ui.R;
import com.car.ui.R2;
import com.car.ui.delegate.shoplist.mvp.ShopListContract;
import com.car.ui.delegate.shoplist.mvp.ShopListPresenterImpl;
import com.elvishew.xlog.XLog;
import com.hjq.toast.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.ui.delegate
 * @time 2019/11/26 21:58
 * @description
 */
@CreatePresenter(ShopListPresenterImpl.class)
public class BaseShopListDelegate extends BaseMvpDelegate<ShopListPresenterImpl>
        implements ShopListContract.IShopListView {

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

    private String mTitle;
    private String mBusinessScope;

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

    private BaseShopListDelegate(String title, String businessScope) {
        this.mTitle = title;
        this.mBusinessScope = businessScope;
    }

    public static BaseShopListDelegate newInstance(@NonNull String title, @NonNull String businessScope) {
        return new BaseShopListDelegate(title, businessScope);
    }

    @Override
    public Object setLayout() {
        return R.layout.base_delegate_shop_list;
    }

    @Override
    public void bindView(View view) {
        setToolbarStyle(Color.WHITE, mTitle);
        if (mBusinessScope.equals(BusinessScope.BUSINESSSCOPE_SHOP_LIST)) {
            getStoreList();
            mBusinessScope = "1";
            getCarService();
            return;
        }
        getCarService();
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

    private void getCarService() {
        getPresenter().requestCarService(RequestParam.builder()
                .addTokenId()
                .addParam("businessScope", mBusinessScope)
                .addParam("areaId2", CarPreference.getAreaId())
                .addParam("currPage", "1")
                .addParam("userLongItude", CarPreference.getLongitude())
                .addParam("userLatItude", CarPreference.getLatitude())
                .build());
    }

    private void getStoreList() {
        getPresenter().requestShopList(RequestParam.builder()
                .addParam("areaId2", CarPreference.getAreaId())
                .addParam("currPage", 1)
                .addParam("userLongItude", CarPreference.getLongitude())
                .addParam("userLatItude", CarPreference.getLatitude())
                .build());
    }

    @Override
    public void resultCarService(String carService) {
        BusinessScopeAndShopListBean bean = gson.fromJson(carService, BusinessScopeAndShopListBean.class);
        XLog.e(bean.getMsg());
    }

    @Override
    public void resultShopList(String shopList) {
        BusinessScopeAndShopListBean bean = gson.fromJson(shopList, BusinessScopeAndShopListBean.class);
        XLog.e(bean.getMsg());
    }
}
