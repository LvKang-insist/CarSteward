package com.car.core.mvp.presenter;

import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModelProvider;

import com.car.core.mvp.model.BaseModel;
import com.car.core.mvp.view.IBaseView;
import com.hjq.toast.ToastUtils;

import java.lang.ref.WeakReference;

/**
 * @author 345 QQ:1831712732
 * @name MvpFrame
 * @class name：com.latte.core.mvp.presenter
 * @time 2019/8/30 17:12
 * @description P层基类 需要被继承，内部对生命周期进行代理，可直接在 P 层使用生命周期
 */
public abstract class BasePresenter<V extends IBaseView, M extends BaseModel>
        implements IBasePresenter<V>  {

    private static final String TAG = "BasePresenter";

    private WeakReference<V> viewRef;

    public V getView() {
        return isViewAttached() ? viewRef.get() : null;
    }

    public M getModel(Class<M> cls) {
        return new ViewModelProvider.NewInstanceFactory().create(cls);
    }

    protected boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    private void attache(V view, Bundle saveInstanceState) {
        viewRef = new WeakReference<>(view);
    }

    @Override
    public void onMvpAttachView(V view, Bundle savedInstanceState) {
        attache(view, savedInstanceState);
    }

    @Override
    public void onMvpSaveInstanceState(Bundle savedInstanceState) {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onMvpCreate(){
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onMvpStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onMvpResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onMvpPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onMvpStop() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onMvpDestroy() {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }
}
