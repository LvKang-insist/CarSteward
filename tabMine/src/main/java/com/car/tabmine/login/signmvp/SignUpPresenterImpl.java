package com.car.tabmine.login.signmvp;

import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.car.core.mvp.presenter.BasePresenter;
import com.car.core.mvp.view.BaseMvpFragment;
import com.google.gson.Gson;
import com.hjq.toast.ToastUtils;

import java.util.WeakHashMap;

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
    public void requestNumberCheck(BaseMvpFragment mvpFragment, String url, WeakHashMap param) {
        getModel(SignUpModel.class)
                .request(url, param)
                .observe(mvpFragment, s -> {
                    //验证手机号
                    JSONObject object = JSON.parseObject(s);
                    if (object.getInteger("status") == 1) {
                        if ("success".equals(object.getString("msg"))) {
                            getView().checkNumberResult(true);
                        }
                    }
                });
    }

    @Override
    public void sendSms(BaseMvpFragment mvpFragment, String url, WeakHashMap param) {
        getModel(SignUpModel.class)
                .request(url, param)
                .observe(mvpFragment, s -> getView().smsResult(s));
    }

    @Override
    public void signUp(BaseMvpFragment mvpFragment, String url, WeakHashMap param) {
        getModel(SignUpModel.class)
                .request(url, param)
                .observe(mvpFragment, s -> getView().signUpResult(s));
    }
}
