package com.car.core.latte;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.XLog;
import com.hjq.toast.ToastUtils;

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
