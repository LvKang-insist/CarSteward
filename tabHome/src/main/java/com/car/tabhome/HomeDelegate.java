package com.car.tabhome;

import android.os.Bundle;
import android.view.View;

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
        implements HomeContract.IHomeView, AMapUtils.LocationCallBack {

    @BindView(R2.id.delegate_home_location)
    AppCompatTextView mLocation = null;
    @BindView(R2.id.delegate_home_news)
    BGABadgeImageView mNews = null;
    @BindView(R2.id.delegate_home_image)
    AppCompatImageView mImage = null;
    @BindView(R2.id.delegate_home_recycler)
    RecyclerView mRecycler = null;
    private HomeConverter mConverter;
    private HomeRvAdapter mAdapter;
    private AMapUtils aMapUtils;

    @Override
    public Object setLayout() {
        return R.layout.home_delegate;
    }

    @Override
    public void onStart() {
        super.onStart();
        startInitPermission();
    }

    @Override
    public int getToolbar() {
        return R.id.delegate_toolbar;
    }

    @Override
    public void bindView(View view) {
        initLocation();
        mConverter = new HomeConverter();
        mAdapter = new HomeRvAdapter(mConverter.convert(), this);
        mRecycler.setLayoutManager(new GridLayoutManager(getContext(), 20));
        mRecycler.setAdapter(mAdapter);
        mLocation.setText(CarPreference.getCity());
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
        getPresenter().onRequestIndex(RequestParam.builder()
                .addTokenId()
                //省级名称
                .addParam("areaName1", "陕西省")
                //市级名称
                .addParam("areaName2", "西安")
                .build());
        getPresenter().onResultIStyles();
    }

    /**
     * 定位结果回调
     *
     * @param location
     */
    @Override
    public void onCallLocationSuc(AMapLocation location) {
        aMapUtils.stopMapLocation();
        mLocation.setText(location.getCity());
        getPresenter().onRequestICityCode(RequestParam.builder()
                .addParam("areaName1", location.getProvider())
                .addParam("areaName2", location.getCity()).build());
        //保存经纬度
        CarPreference.putLongitude(String.valueOf(location.getLongitude()));
        CarPreference.putLatitude(String.valueOf(location.getLatitude()));
    }

    @Override
    public void onResultIndex(String result) {
        IndexBean bean = gson.fromJson(result, IndexBean.class);
        if (bean.getStatus() == 1) {
            if (!bean.getMsgCount().isEmpty() && Integer.parseInt(bean.getMsgCount()) > 0) {
                mNews.showTextBadge(bean.getMsgCount());
            } else {
                mNews.hiddenBadge();
            }
            if (bean.getBrandName() != null && !bean.getBrandName().isEmpty() &&
                    bean.getYearsTypeName() != null && !bean.getYearsTypeName().isEmpty()) {
                mConverter.add(bean);
                mAdapter.notifyDataSetChanged();
            }
        } else {
            ToastUtils.show(bean.getMsg());
        }
    }

    @Override
    public void onResultCityCode(String result) {
        XLog.json(result);
    }

    @Override
    public void onResultStyles(GetStylesBean bean) {
        Latte.stopLoading();
        GlideUtil.setImage(BaseUrl.BASE_URL + bean.getData().getBackgroundImg(), mImage);
        mConverter.addStyle(bean);
        mAdapter.notifyDataSetChanged();
    }

}
