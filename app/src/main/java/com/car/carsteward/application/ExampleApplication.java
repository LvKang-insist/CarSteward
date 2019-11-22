package com.car.carsteward.application;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.multidex.MultiDex;

import com.car.core.latte.Latte;
import com.elvishew.xlog.XLog;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.entry.ApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tinkerpatch.sdk.TinkerPatch;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

/**
 * @author 345 QQ:1831712732
 * @name MvpFrame
 * @class name：com.latte.mvpApp.example
 * @time 2019/8/30 20:27
 * @description
 */
@DefaultLifeCycle(application = ".MyTinkerApplication",
        flags = ShareConstants.TINKER_ENABLE_ALL,
        loadVerifyFlag = false)
public class ExampleApplication extends ApplicationLike {

    public ExampleApplication(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public Context getBaseContext(Context base) {
        MultiDex.install(base);
        return super.getBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(getApplication())
                .withJavaScriptInterface("car")
                .withWebHost("http:www.baidu.com")
                .withToastUtils()
                .withLog()
                .configure();

        // 初始化TinkerPatch SDK, 更多配置可参照API章节中的,初始化 SDK
        TinkerPatch.init(this)
                .reflectPatchLibrary()
                .setPatchRollbackOnScreenOff(true)
                .setPatchRestartOnSrceenOff(true)
                .setFetchPatchIntervalByHours(3);
        // 获取当前的补丁版本
        XLog.e("current patch version is " + TinkerPatch.with().getPatchVersion());
        // 每隔3个小时（通过setFetchPatchIntervalByHours设置）去访问后台时候有更新,通过handler实现轮训的效果
        TinkerPatch.with().fetchPatchUpdateAndPollWithInterval();

        /**
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
//        UMConfigure.init(getApplication(), UMConfigure.DEVICE_TYPE_PHONE, "");
//        UMConfigure.setLogEnabled(true);

        // 选用AUTO页面采集模式
//        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
    }

}
