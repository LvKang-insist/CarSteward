package com.car.tabmine.login.sign.signmvp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.car.core.mvp.presenter.BasePresenter;
import com.car.core.mvp.view.BaseMvpFragment;

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
       /* getModel().request(url, param, result -> {
            //验证手机号
            JSONObject object = JSON.parseObject(result);
            if (object.getInteger("status") == 1) {
                if ("success".equals(object.getString("msg"))) {
                    if (getView() != null) {
                        getView().checkNumberResult(true);
                    }
                }
            }
        });*/
    }

    public void test(BaseMvpFragment fragment, String url, WeakHashMap map) {
        getModel().test(fragment, url, map);
    }

    @Override
    public void sendSms(String url, WeakHashMap param) {
        getModel()
                .requestSms(url, param, result -> {
                    if (getView() != null) {
                        getView().smsResult(result);
                    }
                });

    }

    @Override
    public void signUp(String url, WeakHashMap param) {
        getModel()
                .requestSign(url, param, result -> {
                    if (getView() != null) {
                        getView().signUpResult(result);
                    }
                });
    }


}
