package com.car.carsteward.application;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.car.core.latte.Latte;
import com.elvishew.xlog.XLog;
import com.meituan.android.walle.WalleChannelReader;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tinkerpatch.sdk.TinkerPatch;
import com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike;
import com.tinkerpatch.sdk.server.callback.ConfigRequestCallback;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import java.util.HashMap;


/**
 * @author 345 QQ:1831712732
 * @name MvpFrame
 * @class name：com.latte.mvpApp.example
 * @time 2019/8/30 20:27
 * @description
 */
public class ExampleApplication extends Application {

    private ApplicationLike tinkerApplicationLike;

    private static final String TAG = "ExampleApplication";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withJavaScriptInterface("car")
                .withWebHost("http:www.baidu.com")
                .withToastUtils()
                .withLog()
                .configure();


        initTinkerPatch();

        /*
         * 初始化common库
         * 参数1:上下文，必须的参数，不能为空
         * 参数2:友盟 app key，非必须参数，如果Manifest文件中已配置app key，该参数可以传空，则使用Manifest中配置的app key，否则该参数必须传入
         * 参数3:友盟 channel，非必须参数，如果Manifest文件中已配置channel，该参数可以传空，则使用Manifest中配置的channel，否则该参数必须传入，channel命名请详见channel渠道命名规范
         * 参数4:设备类型，必须参数，传参数为UMConfigure.DEVICE_TYPE_PHONE则表示手机；传参数为UMConfigure.DEVICE_TYPE_BOX则表示盒子；默认为手机
         * 参数5:Push推送业务的secret，需要集成Push功能时必须传入Push的secret，否则传空
         *
         * 如果在 AndroidManifeset.xml 中没有配置 appkey 和 channel 则可以如下配置
         * UMConfigure.init(this, "58edcfeb310c93091c000be2", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "1fe6a20054bcef865eeb0991ee84525b");
         * 我已经配置过，所以如下方式初始化
         */
        //当前渠道
        String channel = WalleChannelReader.getChannel(this);
        UMConfigure.init(this, "5dd53d004ca357c1fc000ed1", channel, UMConfigure.DEVICE_TYPE_PHONE, "");
        UMConfigure.setLogEnabled(true);

        // 选用AUTO页面采集模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
        // 支持在子进程中统计自定义事件
        UMConfigure.setProcessEvent(true);
    }

    private void initTinkerPatch() {

        // 我们可以从这里获得Tinker加载过程的信息
//        if (BuildConfig.TINKER_ENABLE) {
        tinkerApplicationLike = TinkerPatchApplicationLike.getTinkerPatchApplicationLike();
        // 初始化TinkerPatch SDK
        TinkerPatch.init(
                tinkerApplicationLike
//                new TinkerPatch.Builder(tinkerApplicationLike)
//                    .requestLoader(new OkHttp3Loader())
//                    .build()
        )
                .reflectPatchLibrary()
                .setPatchRollbackOnScreenOff(true)
                .setPatchRestartOnSrceenOff(true)
                .setFetchPatchIntervalByHours(3)
        ;
        // 获取当前的补丁版本
        XLog.d("Current patch version is " + TinkerPatch.with().getPatchVersion());

        // fetchPatchUpdateAndPollWithInterval 与 fetchPatchUpdate(false)
        // 不同的是，会通过handler的方式去轮询
        TinkerPatch.with().fetchPatchUpdateAndPollWithInterval();


        TinkerPatch.with().fetchPatchUpdate(true);

        TinkerPatch.with().fetchDynamicConfig(new ConfigRequestCallback() {
            @Override
            public void onSuccess(HashMap<String, String> hashMap) {
                TinkerLog.w(TAG, "request config success, config:" + hashMap);
            }

            @Override
            public void onFail(Exception e) {
                TinkerLog.w(TAG, "request config failed, exception:" + e);
            }
        }, true);
//        }

    }


}
