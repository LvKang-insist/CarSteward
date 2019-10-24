package com.car.carsteward.application;

import android.annotation.SuppressLint;
import android.app.Application;

import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.XLog;
import com.hjq.toast.ToastUtils;
/**
 * @author 345 QQ:1831712732
 * @name MvpFrame
 * @class name：com.latte.mvpApp.example
 * @time 2019/8/30 20:27
 * @description
 */
@SuppressLint("Registered")
public class ExampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtils.init(this);
        setLogger();
    }

    private void setLogger() {
        XLog.init(new LogConfiguration.Builder()
                .t()//允许打印线程信息
                .tag("345")
                .build());
    }
}
