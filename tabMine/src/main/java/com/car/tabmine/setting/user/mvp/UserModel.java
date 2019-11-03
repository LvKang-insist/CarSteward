package com.car.tabmine.setting.user.mvp;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.car.core.mvp.model.BaseModel;
import com.car.core.net.lvdata.CarRequest;
import com.hjq.toast.ToastUtils;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.setting.user.mvp
 * @time 2019/11/3 16:12
 * @description
 */
public class UserModel extends BaseModel {
    @Override
    public void request(String url, WeakHashMap param, LifecycleOwner owner, Observer observer) {
        CarRequest.result(url, param, liveData -> liveData.observe(owner, observer));
    }
}
