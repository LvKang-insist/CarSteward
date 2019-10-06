package com.car.core.mvp.mvpdefault;

import androidx.lifecycle.MutableLiveData;

import com.car.core.mvp.model.BaseModel;
import com.car.core.net.rx.RxRequest;

import java.util.WeakHashMap;

/**
 * @author 345 QQ:1831712732
 * @name MvpFrame
 * @class name：com.latte.core.mvp.mvpdefault
 * @time 2019/9/10 21:56
 * @description M层的默认实现，如果fragment或者activity中没有过多的数据需要处理，则可以直接使用这个M层
 */
public class DefaultModel extends BaseModel {

    private MutableLiveData<String> mLiveData;

    @Override
    public MutableLiveData request(String url, WeakHashMap param) {
        if (mLiveData == null) {
            mLiveData = new MutableLiveData<>();
        }
        RxRequest.onGetRx(url, param, (flag, result) -> {
            if (flag) {
                mLiveData.setValue(result);
            }else {
                mLiveData.setValue(null);
            }
        });
        return mLiveData;
    }
}
