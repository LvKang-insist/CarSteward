package com.car.ui.delegate.shoplist.mvp;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.car.core.mvp.model.BaseModel;
import com.car.core.net.lvdata.CarRequest;

import java.util.WeakHashMap;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.ui.delegate.shoplist.mvp
 * @time 2019/11/27 21:12
 * @description
 */
public class ShopListModel extends BaseModel {
    @Override
    public void request(String url, WeakHashMap param, LifecycleOwner owner, Observer observer) {
        CarRequest.result(url, param, liveData -> liveData.observe(owner, observer));
    }
}
