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
public class SignUpModel extends BaseModel {

    @Override
    public void request(String url, WeakHashMap param,OnResultListener listener) {
        RxRequest.onGetRx(url, param, (flag, result) -> {
            if (flag) {
               listener.result(result);
            } else {
               listener.result(null);
            }
        });
    }

    public void requestSms(String url, WeakHashMap param,OnResultListener listener) {
        RxRequest.onGetRxCookie(url, param, (flag, result) -> {
            if (flag) {
                listener.result(result);

            } else {
                listener.result(null);
            }
        });
    }

    public void requestSign(String url, WeakHashMap param,OnResultListener listener) {
        RxRequest.onAddCookieRxObs(url, param, (flag, result) -> {
            if (flag) {
                listener.result(result);
            } else {
                listener.result(null);
            }
        });
    }


}
