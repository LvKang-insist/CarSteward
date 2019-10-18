package com.car.carsteward.application;

import android.annotation.SuppressLint;
import android.app.Application;

import com.hjq.toast.ToastUtils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

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
        FormatStrategy formatStrategy = setLogger();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }

    private FormatStrategy setLogger() {
        return PrettyFormatStrategy.newBuilder()
                    .tag("345")
                    .build();
    }
}
