package com.car.tabmine.login.sign.signmvp;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.car.core.mvp.presenter.BasePresenter;
import com.car.core.mvp.view.BaseMvpFragment;
import com.car.core.utils.storage.CarPreference;

import java.io.IOException;
import java.util.WeakHashMap;

import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.login.signmvp
 * @time 2019/10/16 20:28
 * @description
 */

public class SignUpPresenterImpl extends BasePresenter<SignUpContract.IsignUpView, SignUpModel>
        implements SignUpContract.IsingUpPresenter {

    @Override
    protected SignUpModel attachModel() {
        return new SignUpModel();
    }

    @Override
    public void requestNumberCheck(String url, WeakHashMap param) {
        getModel().request(url, param, (LifecycleOwner) getView(), s -> {
            Log.e("-----", "requestNumberCheck: "+s );
            JSONObject object = JSON.parseObject((String) s);
            if (object.getInteger("status") == 1) {
                if ("success".equals(object.getString("msg"))) {
                    if (getView() != null) {
                        getView().checkNumberResult(true);
                    }
                }
            }
        });

    }


    @Override
    public void sendSms(String url, WeakHashMap param) {
        getModel()
                .requestSms(url, param, (LifecycleOwner) getView(), response -> {
                    Headers headers = response.headers();
                    String cookie = headers.get("set-cookie");
                    Log.e("---------", "获取 intercept: " + cookie);
                    // 取得 sessid
                    if (cookie != null) {
                        CarPreference.putCookie(cookie.substring(0, cookie.indexOf(";")));
                    }
                    getView().smsResult(response.body().toString());
                });

    }

    @Override
    public void signUp(String url, WeakHashMap param) {
        getModel().requestSign(url, param, (LifecycleOwner) getView(), s -> getView().signUpResult(s));
    }


}
