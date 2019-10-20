package com.car.core.latte;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.car.core.mvp.view.BaseMvpActivity;

import java.util.Map;

/**
 * @author 345 QQ:1831712732
 * @name MvpFrame
 * @class name：com.latte.core.latte
 * @time 2019/8/30 17:12
 * @description 获取配置信息
 */
public class Latte {

    private static Handler handler = new Handler(Looper.getMainLooper());

    public static LatteConfigurator init(Context context) {
        getConfiguration().put(ConfigKeys.CONTEXT, context.getApplicationContext());
        return getInstance();
    }

    /**
     * @return 返回一个 全局Context
     */
    public static Context getContext() {
        return (Context) getConfiguration().get(ConfigKeys.CONTEXT);
    }

    /**
     * @return 返回一个 基类Activity
     */
    public static BaseMvpActivity getBaseMvpActivity() {
        return (BaseMvpActivity) getConfiguration().get(ConfigKeys.BASE_ACTIVITY);
    }

    /**
     * @return 返回一个全局 handler
     */
    public static Handler getHandler() {
        return handler;
    }

    public static <T> T getValue(Object key) {
        return (T) getInstance().getConfigurator(key);
    }

    private static Map<Object, Object> getConfiguration() {
        return getInstance().getLatteConfigs();
    }

    private static LatteConfigurator getInstance() {
        return LatteConfigurator.getInstance();
    }
}
