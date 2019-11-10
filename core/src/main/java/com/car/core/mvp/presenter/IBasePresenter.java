package com.car.core.mvp.presenter;

import android.os.Bundle;

import androidx.lifecycle.LifecycleObserver;

import com.car.core.mvp.view.IBaseView;

import java.util.WeakHashMap;

/**
 * @author 345 QQ:1831712732
 * @name MvpFrame
 * @class name：com.latte.core.mvp.presenter
 * @time 2019/8/30 17:12
 * @description P层默认接口
 */
public interface IBasePresenter<V extends IBaseView> extends LifecycleObserver {

    /**
     * onCreateView() 执行完后立即回调这个方法
     */
    void onMvpAttachView(V view, Bundle savedInstanceState);

    void onMvpSaveInstanceState(Bundle savedInstanceState);

}
