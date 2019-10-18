package com.car.tabmine.login.signmvp;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.car.core.mvp.model.BaseModel;
import com.car.core.net.rx.RxRequest;

import java.util.WeakHashMap;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.login.signmvp
 * @time 2019/10/16 20:30
 * @description
 */
public class SignUpModel extends BaseModel<String> {

    @Override
    public MutableLiveData<String> request(String url, WeakHashMap param) {
        MutableLiveData<String> liveData = getLiveData();
        RxRequest.onGetRx(url, param, (flag, result) -> {
            if (flag){
                liveData.setValue(result);
            }else {
                liveData.setValue(null);
            }
        });
        return liveData;
    }

    public MutableLiveData<String> requestSms(String url, WeakHashMap param) {
        MutableLiveData<String> liveData = getLiveData();
        RxRequest.onGetRxCookie(url, param, (flag, result) -> {
            if (flag){
                liveData.setValue(result);
            }else {
                liveData.setValue(null);
            }
        });
        return liveData;
    }

    public MutableLiveData<String>
    requestSign(String url, WeakHashMap param) {
        MutableLiveData<String> liveData = getLiveData();
        RxRequest.onAddCookieRxObs(url, param, (flag, result) -> {
            if (flag){
                liveData.setValue(result);
            }else {
                liveData.setValue(null);
            }
        });
        return liveData;
    }


}
