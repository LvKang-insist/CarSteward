package com.car.ui.delegate.shoplist;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.mvp.mvpdefault.DefaultContract;
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl;
import com.car.core.mvp.presenter.IBasePresenter;
import com.car.core.mvp.view.BaseMvpDelegate;
import com.car.core.utils.bean.BusinessScopeAndShopListBean;
import com.car.core.utils.dimen.DimenUtil;
import com.car.core.utils.storage.CarPreference;
import com.car.core.utils.util.BusinessScope;
import com.car.core.utils.util.BusinessScopeList;
import com.car.core.utils.util.Code;
import com.car.core.utils.util.RequestParam;
import com.car.ui.R;
import com.car.ui.R2;
import com.car.ui.delegate.shoplist.adapter.DefaultSelectAdapter;
import com.car.ui.delegate.shoplist.mvp.ShopListContract;
import com.car.ui.delegate.shoplist.mvp.ShopListPresenterImpl;
import com.elvishew.xlog.XLog;
import com.hjq.toast.ToastUtils;
import com.umeng.commonsdk.debug.D;

import java.util.ArrayList;
import java.util.List;

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
        implements ShopListContract.IShopListView, SwipeRefreshLayout.OnRefreshListener {

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
    @BindView(R2.id.base_delegate_shop_refresh)
    SwipeRefreshLayout mRefreshLayout;

    private String mTitle;
    private String mBusinessScope;
    private ListPopupWindow mPopWindwo;
    private List<String> city;

    @OnClick({R2.id.base_delegate_shop_select_city,
            R2.id.base_delegate_shop_select_service,
            R2.id.base_delegate_shop_select_sort,
            R2.id.base_delegate_shop_select_filtrate})
    void selectClick(View view) {
        final int id = view.getId();
        defaultSetting();
        ArrayList<String> list = new ArrayList<>();
        list.add("美容");
        list.add("改装");
        list.add("装横");
        list.add("洗车");
        list.add("保养");
        list.add("换轮胎");
        if (id == OnSelectType.TYPE_CITY_ID) {
            mCityLine.setVisibility(View.VISIBLE);
            DefaultSelectAdapter adapter = new DefaultSelectAdapter(city, getContext(), R.layout.item_tv);
            showPopWindow(adapter);
        } else if (id == OnSelectType.TYPE_SERVICE_ID) {
            DefaultSelectAdapter adapter = new DefaultSelectAdapter(BusinessScopeList.getBusinessScopeNames(),
                    getContext(), R.layout.item_tv);
            showPopWindow(adapter);
            mServiceLine.setVisibility(View.VISIBLE);
        } else if (id == OnSelectType.TYPE_SORT_ID) {
            DefaultSelectAdapter adapter = new DefaultSelectAdapter(list, getContext(), R.layout.item_tv);
            showPopWindow(adapter);
            mSortLine.setVisibility(View.VISIBLE);
        } else {
            DefaultSelectAdapter adapter = new DefaultSelectAdapter(list, getContext(), R.layout.item_tv);
            showPopWindow(adapter);
            mFiltrateLine.setVisibility(View.VISIBLE);
        }
    }

    private BaseShopListDelegate(String title, String businessScope) {
        this.mTitle = title;
        this.mBusinessScope = businessScope;
        city = new ArrayList<>();
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
        isRequest();
        mRefreshLayout.setColorSchemeResources(R.color.yellow, R.color.red, R.color.blue);
        mRefreshLayout.setOnRefreshListener(this);
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

    private void getCarService(int page) {
        getPresenter().requestCarService(RequestParam.builder()
                .addTokenId()
                .addParam("businessScope", mBusinessScope)
                .addParam("areaId2", CarPreference.getAreaId())
                .addParam("currPage", page)
                .addParam("userLongItude", CarPreference.getLongitude())
                .addParam("userLatItude", CarPreference.getLatitude())
                .build());
    }

    private void getStoreList(int page) {
        getPresenter().requestShopList(RequestParam.builder()
                .addParam("areaId2", CarPreference.getAreaId())
                .addParam("currPage", page)
                .addParam("userLongItude", CarPreference.getLongitude())
                .addParam("userLatItude", CarPreference.getLatitude())
                .build());
    }

    private void isRequest() {
        if (mBusinessScope.equals(BusinessScope.BUSINESSSCOPE_SHOP_LIST)) {
            getStoreList(1);
            return;
        }
        getCarService(1);
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        isRequest();
    }

    @Override
    public void resultCarService(String carService) {
        success(gson.fromJson(carService, BusinessScopeAndShopListBean.class));
    }

    @Override
    public void resultShopList(String shopList) {
        success(gson.fromJson(shopList, BusinessScopeAndShopListBean.class));
    }

    private void success(BusinessScopeAndShopListBean bean) {
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
        if (bean.getStatus() == Code.SUCCESS) {
            BusinessScopeAndShopListBean.DataBean data = bean.getData();
            for (int i = 0; i < data.getCity().size(); i++) {
                city.add(data.getCity().get(i).getAreaName());
            }
        }
    }

    private void showPopWindow(BaseAdapter adapter) {
        mPopWindwo = new ListPopupWindow(getContext());
        mPopWindwo.setAdapter(adapter);
        mPopWindwo.setWidth(ListPopupWindow.MATCH_PARENT);
        mPopWindwo.setHeight(DimenUtil.getScreenHeight() / 3);
        mPopWindwo.setDropDownGravity(Gravity.START);
        mPopWindwo.setBackgroundDrawable(new ColorDrawable(0xFFFFFF));
        mPopWindwo.setAnchorView(mCityLine);
        mPopWindwo.show();
        mPopWindwo.setOnDismissListener(this::defaultSetting);
    }




}
