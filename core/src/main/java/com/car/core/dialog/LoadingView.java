package com.car.core.dialog;

import android.annotation.SuppressLint;
import android.view.Gravity;

import com.car.core.latte.Latte;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.ui.dialog
 * @time 2019/11/26 21:14
 * @description 全局加载对话框
 */
public class LoadingView {

    @SuppressLint("StaticFieldLeak")
    private static ToastDialog mLoadingDilaog;
    private static boolean mDialogIsShow = false;

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
                .setContentView(com.car.core.R.layout.dialog_toast)
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
                    .show(Latte.getBaseMvpActivity().getSupportFragmentManager(), "latte");
        }
    }
}
