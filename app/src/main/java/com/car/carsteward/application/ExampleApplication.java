package com.car.carsteward.application;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.car.core.latte.Latte;
import com.car.core.utils.util.AndFixManager;
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
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AndFixManager.getInstance().initPatch(this);
        Latte.init(this)
                .withJavaScriptInterface("car")
                .withWebHost("http:www.baidu.com")
                .withToastUtils()
                .withLog()
                .configure();
    }

}
