package com.car.core.mvp.model;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.car.core.net.SingleSourceLiveData;

import java.util.WeakHashMap;

/**
 * @author 345 QQ:1831712732
 * @name MvpFrame
 * @class name：com.latte.core.mvp.model
 * @time 2019/8/30 17:12
 * @description 抽象 M 层 需要被继承，默认一个请求数据的方法，可重写或者重载，也可以自定义
 */
public abstract class BaseModel<T> extends ViewModel {

    private SingleSourceLiveData<T> sourceLiveData;

    public SingleSourceLiveData<T> getSourceLiveData() {
        if (sourceLiveData == null) {
            sourceLiveData = new SingleSourceLiveData<>();
        }
        return sourceLiveData;
    }


    public interface OnResultListener {
        /**
         * 数据的回调方法
         * @param result 结果
         */
        void result(String result);
    }

    /**
     * 默认的网络请求方法
     *
     * @param url   地址
     * @param param 参数
     * @param owner
     */
    public abstract void request(String url, WeakHashMap param, LifecycleOwner owner, Observer<String> observer);
}
