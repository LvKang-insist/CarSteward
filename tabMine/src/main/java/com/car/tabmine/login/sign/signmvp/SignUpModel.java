package com.car.tabmine.login.sign.signmvp;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.car.core.mvp.model.BaseModel;
import com.car.core.net.CustomResponse;
import com.car.core.net.lvdata.CarRequest;

import java.util.WeakHashMap;

import retrofit2.Response;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.login.signmvp
 * @time 2019/10/16 20:30
 * @description
 */
public class SignUpModel extends BaseModel {

    @Override
    public void request(String url, WeakHashMap param, LifecycleOwner owner, Observer observer) {
        CarRequest.result(url, param, liveData -> liveData.observe(owner, observer));
    }


    public void requestSms(String url, WeakHashMap param, LifecycleOwner owner, Observer<CustomResponse> observer) {

        CarRequest.getCookie(url, param, liveData -> liveData.observe(owner,observer));
    }

    public void requestSign(String url, WeakHashMap param, LifecycleOwner owner, Observer<String> observer) {
        CarRequest.onAddCookieRxObs(url, param, liveData -> liveData.observe(owner, observer));
    }


}
