package com.car.core.utils.map;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.elvishew.xlog.XLog;
import com.hjq.toast.ToastUtils;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.core.utils.map
 * @time 2019/11/10 16:40
 * @description
 */
public class AMapUtils implements AMapLocationListener {

    /**
     * 定位结果
     */
    public interface LocationCallBack {
        /**
         * 定位结果的回调
         *
         * @param location
         */
        void onCallLocationSuc(AMapLocation location);
    }

    /**
     * 声明mlocationClient对象
     */
    private AMapLocationClient mLocationClient;
    /**
     * 声明mLocationOption对象
     */
    public AMapLocationClientOption mLocationOption = null;
    private LocationCallBack mLocationCallBack;
    private Context mContext;

    public AMapUtils(Context context, LocationCallBack locationCallback) {
        mContext = context;
        mLocationCallBack = locationCallback;
        initLocation();
    }

    /**
     * 初始化定位参数
     */
    private void initLocation() {
        mLocationClient = new AMapLocationClient(mContext);
        mLocationOption = new AMapLocationClientOption();
        // 设置定位监听
        mLocationClient.setLocationListener(this);
        // 设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        // 设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        // 设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
    }

    /**
     * 开启定位
     */
    public void startMapLocation() {
        //判断是否开启了，没有开启就开启
        if (!mLocationClient.isStarted()) {
            mLocationClient.startLocation();
        }
    }

    /**
     * 停止定位服务
     */
    public void stopMapLocation() {
        //判断服务是否开启了，若开启了则停止
        if (mLocationClient.isStarted()) {
            mLocationClient.stopLocation();
        }
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                // 可在其中解析amapLocation获取相应内容。
                //给接口设置数据
                mLocationCallBack.onCallLocationSuc(aMapLocation);
                stopMapLocation();
            } else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                XLog.e("AmapError # location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
                ToastUtils.show("定位失败");
                stopMapLocation();
            }
        } else {
            stopMapLocation();
        }
    }


    /**
     * 设置接口回调
     *
     * @param locationCallBack
     */
    public void setLocationCallBack(LocationCallBack locationCallBack) {
        mLocationCallBack = locationCallBack;
    }


}
