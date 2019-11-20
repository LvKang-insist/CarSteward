package com.car.tabhome;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.location.AMapLocation;
import com.car.core.api.BaseUrl;
import com.car.core.delegate.BottomItemDelegate;
import com.car.core.latte.Latte;
import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.utils.bean.GetStylesBean;
import com.car.core.utils.bean.IndexBean;
import com.car.core.utils.map.AMapUtils;
import com.car.core.utils.storage.CarPreference;
import com.car.core.utils.util.GlideUtil;
import com.car.core.utils.util.RequestParam;
import com.car.tabhome.home.adapter.HomeConverter;
import com.car.tabhome.home.adapter.HomeRvAdapter;
import com.car.tabhome.home.mvp.HomeContract;
import com.car.tabhome.home.mvp.HomePersenterImpl;
import com.elvishew.xlog.XLog;
import com.hjq.toast.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import cn.bingoogolapple.badgeview.BGABadgeImageView;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabhome
 * @time 2019/10/7 22:54
 * @description
 */
@CreatePresenter(HomePersenterImpl.class)
public class HomeDelegate extends BottomItemDelegate<HomePersenterImpl>
        implements HomeContract.IHomeView, AMapUtils.LocationCallBack, OnRefreshListener {

    @BindView(R2.id.delegate_home_location)
    AppCompatTextView mLocation = null;
    @BindView(R2.id.delegate_home_news)
    BGABadgeImageView mNews = null;
    @BindView(R2.id.delegate_home_image)
    AppCompatImageView mImage = null;
    @BindView(R2.id.delegate_home_recycler)
    RecyclerView mRecycler = null;
    @BindView(R2.id.delegate_home_refresh_layout)
    SmartRefreshLayout mRefresh = null;
    private HomeConverter mConverter;
    private HomeRvAdapter mAdapter;
    private AMapUtils aMapUtils;
    private boolean isRefresh = false;

    @Override
    public Object setLayout() {
        return R.layout.home_delegate;
    }

    @Override
    public void onStart() {
        super.onStart();
        //获取权限
        startInitPermission(isResult -> {
            if (isResult) {
                initLocation();
            }
        });
    }

    @Override
    public int getToolbar() {
        return R.id.delegate_toolbar;
    }

    @Override
    public void bindView(View view) {
        setCity();
        mConverter = new HomeConverter();
        mAdapter = new HomeRvAdapter(mConverter.convert(), this);
        mRecycler.setLayoutManager(new GridLayoutManager(getContext(), 20));
        mRecycler.setAdapter(mAdapter);
        mRefresh.setOnRefreshListener(this);
    }

    /**
     * 设置当前位置
     */
    private void setCity() {
        if (CarPreference.getCity() != null) {
            mLocation.setText(CarPreference.getCity());
        } else {
            ToastUtils.show("使用默认位置");
            mLocation.setText(BaseUrl.DEFAULTCITY);
        }
    }

    /**
     * 开始定位
     */
    private void initLocation() {
        aMapUtils = new AMapUtils(getContext(), this);
        aMapUtils.startMapLocation();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        Latte.showLoading("");
        requestIndex(CarPreference.getAreaId());
        getPresenter().onResultIStyles();
    }

    /**
     * 定位结果回调
     *
     * @param location
     */
    @Override
    public void onCallLocationSuc(AMapLocation location) {
        XLog.e("定位成功 当前位置 #" + location.getCity());
        aMapUtils.stopMapLocation();
        getPresenter().onRequestICityCode(RequestParam.builder()
                .addParam("areaName1", location.getProvince())
                .addParam("areaName2", location.getCity()).build());
        //保存经纬度
        CarPreference.putLongitude(String.valueOf(location.getLongitude()));
        CarPreference.putLatitude(String.valueOf(location.getLatitude()));
    }

    /**
     * 刷新回调
     *
     * @param refreshLayout
     */
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        isRefresh = true;
        requestIndex(CarPreference.getAreaId());
        getPresenter().onResultIStyles();
    }

    @Override
    public void onResultIndex(String result) {
        if (isRefresh) {
            mRefresh.finishRefresh();
        }
        IndexBean bean = gson.fromJson(result, IndexBean.class);
        if (bean.getStatus() == 1) {
            if (!bean.getMsgCount().isEmpty() && Integer.parseInt(bean.getMsgCount()) > 0) {
                mNews.showTextBadge(bean.getMsgCount());
            } else {
                mNews.hiddenBadge();
            }
            mConverter.add(bean);
            mAdapter.notifyDataSetChanged();
        } else {
            ToastUtils.show(bean.getMsg());
        }
    }

    @Override
    public void onResultCityCode(String cityCode) {
        if (cityCode == null) {
            requestIndex(BaseUrl.DEFAULTCITYAREAID);
            return;
        }
        //位置发生变化
        if (CarPreference.getAreaId() != null &&
                !CarPreference.getAreaId().equals(cityCode)) {
            ToastUtils.show("当前位置发生变化，正在切换位置");
            CarPreference.putAreaId(cityCode);
            CarPreference.putCity(mLocation.getText().toString());
            setCity();
            requestIndex(cityCode);
        }
    }

    public void requestIndex(String areaid) {
        getPresenter().onRequestIndex(RequestParam.builder()
                .addTokenId()
                .addParam("areaId2", areaid)
                .build());
    }

    @Override
    public void onResultStyles(GetStylesBean bean) {
        Latte.stopLoading();
        GlideUtil.setImage(BaseUrl.BASE_URL + bean.getData().getBackgroundImg(), mImage);
        mConverter.addStyle(bean);
        mAdapter.notifyDataSetChanged();
    }

}
