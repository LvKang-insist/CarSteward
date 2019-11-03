package com.car.core.latte;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;

import com.car.core.R;
import com.car.core.mvp.view.BaseMvpActivity;
import com.car.core.net.callback.IFailure;
import com.car.core.ui.dialog.ToastDialog;
import com.google.gson.Gson;

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
    private static Gson gson = new Gson();
    private static boolean mDialogIsShow = false;
    private static ToastDialog mLoadingDilaog;

    public static LatteConfigurator init(Application context) {
        getConfiguration().put(ConfigKeys.APP_CONTEXT, context.getApplicationContext());
        return getInstance();
    }

    /**
     * @return 返回一个 全局Context
     */
    public static Application getAppContext() {
        return (Application) getConfiguration().get(ConfigKeys.APP_CONTEXT);
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

    public static Gson getGson() {
        return gson;
    }

    /**
     * 显示加载对话框
     *
     * @param msg 若为 null ，显示正在加载，否则显示 msg
     */
    public static void showLoading(String msg) {
        if (mLoadingDilaog == null) {
            createLoadingDialog(msg);
        }
        if (!mLoadingDilaog.isVisible()) {
            mLoadingDilaog
                    .setType(ToastDialog.Type.LOADING)
                    .show(getBaseMvpActivity().getSupportFragmentManager(), "latte");
        }
    }

    /**
     * 初始化 loading
     *
     * @param msg 消息
     */
    private static void createLoadingDialog(String msg) {
        if (msg == null) {
            msg = "正在加载...";
        }
        mLoadingDilaog = ToastDialog.ToastBuilder()
                .setContentView(R.layout.dialog_toast)
                .setGravity(Gravity.CENTER)
                .build()
                .setMessage(msg);
        mDialogIsShow = true;
    }

    /**
     * 关闭 Loading
     */
    public static void stopLoading() {
        if (mLoadingDilaog != null && mDialogIsShow) {
            mLoadingDilaog.dismiss();
            mLoadingDilaog = null;
        }
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
