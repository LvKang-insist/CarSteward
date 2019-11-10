package com.car.tabhome.home.mvp;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.car.core.mvp.model.BaseModel;
import com.car.core.net.lvdata.CarRequest;

import java.util.WeakHashMap;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabhome.home.mvp
 * @time 2019/11/10 17:53
 * @description
 */
public class HomeModel extends BaseModel {
    @Override
    public void request(String url, WeakHashMap param, LifecycleOwner owner, Observer observer) {
        CarRequest.result(url, param, liveData -> liveData.observe(owner, observer));
    }
}
