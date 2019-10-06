package com.car.core.latte;

import android.app.Activity;

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

    public LatteConfigurator withBaseMvpActivity(Activity activity) {
        LATTE_CONFIG.put(ConfigKeys.BASE_ACTIVITY, activity);
        return this;
    }

    /**
     * 配置完成时调用
     */
    public void configure() {
        LATTE_CONFIG.put(ConfigKeys.CONFIG_READER, true);
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
