package com.car.tabmine.login.signmvp;

import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.car.core.mvp.model.BaseModel;
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
    protected SignUpModel attachModel() {
        return new SignUpModel();
    }

    @Override
    public void requestNumberCheck(String url, WeakHashMap param) {
        SignUpModel model = getModel();
        if (model != null) {
            model
                    .request(url, param, result -> {
                        //验证手机号
                        JSONObject object = JSON.parseObject(result);
                        if (object.getInteger("status") == 1) {
                            if ("success".equals(object.getString("msg"))) {
                                getView().checkNumberResult(true);
                            }
                        }
                    });
        }

    }

    @Override
    public void sendSms(String url, WeakHashMap param) {
        getModel()
                .requestSms(url, param, result -> getView().smsResult(result));

    }

    @Override
    public void signUp(String url, WeakHashMap param) {
        getModel()
                .requestSign(url, param, result -> getView().signUpResult(result));
    }


}
