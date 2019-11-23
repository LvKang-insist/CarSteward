package com.car.core.latte;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;

import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.XLog;
import com.hjq.toast.ToastUtils;
import com.meituan.android.walle.WalleChannelReader;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author 345 QQ:1831712732
 * @name MvpFrame
 * @class name：com.latte.core.latte
 * @time 2019/8/30 17:12
 * @description 对全局进行配置，注意最后调用 configure() 完成配置
 */
public class LatteConfigurator {

    private HashMap<Object, Object> LATTE_CONFIG = new LinkedHashMap<>();

    static class LatteConfiguratorHolder {
        static final LatteConfigurator INSTANCE = new LatteConfigurator();
    }

    public static LatteConfigurator getInstance() {
        return LatteConfiguratorHolder.INSTANCE;
    }


    /**
     * 配置完成时调用
     */
    public void configure() {
        LATTE_CONFIG.put(ConfigKeys.CONFIG_READER, true);
    }

    public void withBaseMvpActivity(Activity activity) {
        LATTE_CONFIG.put(ConfigKeys.BASE_ACTIVITY, activity);
    }

    public LatteConfigurator withJavaScriptInterface(@NonNull String name) {
        LATTE_CONFIG.put(ConfigKeys.JAVASCRIPT_INTERFACE, name);
        return this;
    }

    /**
     * 浏览器加载的 HOST
     */
    public LatteConfigurator withWebHost(String host) {
        LATTE_CONFIG.put(ConfigKeys.WEB_HOST, host);
        return this;
    }

    /**
     * 初始化 Toast
     */
    public LatteConfigurator withToastUtils() {
        ToastUtils.init(Latte.getAppContext());
        return this;
    }

    /**
     * 初始化 Toast
     */
    public LatteConfigurator withLog() {
        XLog.init(new LogConfiguration.Builder()
                .t()//允许打印线程信息
                .tag("345")
                .build());
        return this;
    }

    /**
     * 初始化common库
     * 参数1:上下文，必须的参数，不能为空
     * 参数2:友盟 app key，非必须参数，如果Manifest文件中已配置app key，该参数可以传空，则使用Manifest中配置的app key，否则该参数必须传入
     * 参数3:友盟 channel，非必须参数，如果Manifest文件中已配置channel，该参数可以传空，则使用Manifest中配置的channel，否则该参数必须传入，channel命名请详见channel渠道命名规范
     * 参数4:设备类型，必须参数，传参数为UMConfigure.DEVICE_TYPE_PHONE则表示手机；传参数为UMConfigure.DEVICE_TYPE_BOX则表示盒子；默认为手机
     * 参数5:Push推送业务的secret，需要集成Push功能时必须传入Push的secret，否则传空
     * <p>
     * 如果在 AndroidManifeset.xml 中没有配置 appkey 和 channel 则可以如下配置
     * UMConfigure.init(this, "58edcfeb310c93091c000be2", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "1fe6a20054bcef865eeb0991ee84525b");
     * 我已经配置过，所以如下方式初始化
     */
    public LatteConfigurator withUMConfig(Application application) {
        //获取当前渠道
        String channel = WalleChannelReader.getChannel(application);
        UMConfigure.init(application, "5dd53d004ca357c1fc000ed1", channel, UMConfigure.DEVICE_TYPE_PHONE, "");
        UMConfigure.setLogEnabled(true);
        // 选用AUTO页面采集模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
        // 支持在子进程中统计自定义事件
        UMConfigure.setProcessEvent(true);
        return this;
    }

    /**
     * @return 返回全部配置
     */
    public HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIG;
    }

    /**
     * 返回某一项配置
     */
    public <T> T getConfigurator(Object key) {
        Object o = LATTE_CONFIG.get(key);
        if (o == null) {
            throw new NullPointerException(key.toString() + "is NULL");
        }
        return (T) o;
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIG.get(ConfigKeys.CONFIG_READER.name());
        //如果配置未完成，抛出运行时异常
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

}
