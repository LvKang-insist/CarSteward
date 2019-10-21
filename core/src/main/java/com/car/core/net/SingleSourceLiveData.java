package com.car.core.net;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

/**
 * @author
 * 设置并监听单一数据源时使用 LiveData
 * 方便于当需要切换数据源时自动取消掉前一个数据源的监听
 *
 * @param <T> 监听的数据源类型
 */
public class SingleSourceLiveData<T> extends MutableLiveData<T> {
    private LiveData<T> lastSource;
    private T lastData;
    private final Observer<T> observer = new Observer<T>() {
        @Override
        public void onChanged(T t) {
            if (t != null && t == lastData) {
                return;
            }
            lastData = t;
            Log.e("Demo", "onChanged: "+t );
            setValue(t);
        }
    };

    /**
     * 设置数据源，当有已设置过的数据源时会取消该数据源的监听
     * 适用于用于频繁点击多次请求时，保证请求只有一次
     *
     * @param source
     */
    public void setSource(LiveData<T> source) {
        if (lastSource == source) {
            return;
        }

        if (lastSource != null) {
            //移除监听
            lastSource.removeObserver(observer);
        }
        lastSource = source;

        //如果此livedata存在观察者
        if (hasActiveObservers()) {
            //添加到观察者列表
            Log.e("Demo", "setSource: " );
            lastSource.observeForever(observer);
        }
    }

    /**
     * 开始观察时
     */
    @Override
    protected void onActive() {
        super.onActive();
        Log.e("Demo", "onActive: " );
        if (lastSource != null) {
            lastSource.observeForever(observer);
        }
    }

    /**
     * 没有任何活动的观察者时
     * 注意：这里并没有说没有任何观察者，只是可能当你进入下一个界面时，
     * 当前观察者状态并不是 Lifecycle.State#Start或 Lifecycle.State#RESUMED
     */
    @Override
    protected void onInactive() {
        super.onInactive();

        if (lastSource != null) {
            lastSource.removeObserver(observer);
        }
    }
}
