package com.car.tabmine.login.sign.signmvp;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.car.core.mvp.model.BaseModel;
import com.car.core.net.CustomResponse;
import com.car.core.net.rx.CarRequest;

import java.io.IOException;
import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
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


    public void requestSms(String url, WeakHashMap param, LifecycleOwner owner, Observer<Response> observer) {

        CarRequest.getCookie(url, param, new CarRequest.OnResponseListener() {
            @Override
            public void onNext(LiveData<CustomResponse> liveData) {
                liveData.observeForever(new Observer<CustomResponse>() {
                    @Override
                    public void onChanged(CustomResponse customResponse) {
                      if (customResponse == null){
                          Log.e("-----", "onChanged:空" );
                      }else {
                          if (customResponse.getResponse() == null){
                              Log.e("-----", "getResponse:空" );
                          }else {
                              Log.e("-----", "getResponse:不空" );
                          }
                      }
                    }
                });
            }
        });
    }

    public void requestSign(String url, WeakHashMap param, LifecycleOwner owner, Observer<String> observer) {
        CarRequest.onAddCookieRxObs(url, param, liveData -> liveData.observe(owner, observer));
    }


}
