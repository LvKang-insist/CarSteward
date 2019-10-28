package com.car.core.mvp.presenter;

import android.os.Bundle;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.car.core.latte.Latte;
import com.car.core.mvp.model.BaseModel;
import com.car.core.mvp.view.IBaseView;
import com.elvishew.xlog.XLog;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 345 QQ:1831712732
 * @name MvpFrame
 * @class name：com.latte.core.mvp.presenter
 * @time 2019/8/30 17:12
 * @description P层基类 需要被继承，内部对生命周期进行代理，可直接在 P 层使用生命周期
 */
public abstract class BasePresenter<V extends IBaseView, M extends BaseModel>
        implements IBasePresenter<V> {

    private WeakReference<V> viewRef;

    private M modelRef;

    private int retryCount = 0;

    /**
     * 获取 M 层对象
     *
     * @return
     */
    protected abstract M attachModel();


    public V getView() {
        return (V) Proxy.newProxyInstance(viewRef.get().getClass().getClassLoader(), viewRef.get().getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (viewRef.get() != null && isArgs(args)) {
                    return method.invoke(viewRef.get(), args);
                } else if (!isArgs(args)) {
                    XLog.e("更新 View 层参数获取失败：" + proxy.toString() + "方法名字为：" + method.getName());
                    //重试机制，如果三次未成功，则不在重试
                    if (retryCount < 3) {
                        retryCount++;
                        retryRequest();
                    } else {
                        Latte.stopLoading();
                        retryCount = 0;
                    }
                }
                return null;
            }
        });
    }

    /**
     * 判断参数是否为 null
     *
     * @param args
     * @return true 表示 不为空，否则为 null
     */
    private boolean isArgs(Object[] args) {
        if (args != null) {
            for (Object arg : args) {
                if (arg == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public LifecycleOwner getLifecycleOwner() {
        return (LifecycleOwner) viewRef.get();
    }

    public M getModel() {
        return isModelAttached() ? modelRef : null;
    }

    protected boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    protected boolean isModelAttached() {
        return modelRef != null;
    }

    private void attache(V view, Bundle saveInstanceState) {
        viewRef = new WeakReference<>(view);
        modelRef = attachModel();
    }

    /**
     * 此方法将会在网络请求的结果为 null 时调用，如果重写此方法，则可以进行重新请求
     */
    public void retryRequest() {

    }

    @Override
    public void onMvpAttachView(V view, Bundle savedInstanceState) {
        attache(view, savedInstanceState);
    }

    @Override
    public void onMvpSaveInstanceState(Bundle savedInstanceState) {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onMvpCreate() {
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
