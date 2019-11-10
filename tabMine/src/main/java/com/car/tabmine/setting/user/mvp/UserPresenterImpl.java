package com.car.tabmine.setting.user.mvp;

import android.os.Bundle;

import androidx.lifecycle.Observer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.car.core.mvp.presenter.BasePresenter;
import com.car.core.utils.storage.CarPreference;
import com.car.tabmine.login.sign.signmvp.SignUpContract;
import com.car.tabmine.login.sign.signmvp.SignUpModel;
import com.elvishew.xlog.XLog;

import java.util.WeakHashMap;

import okhttp3.Headers;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.setting.user.mvp
 * @time 2019/11/3 16:12
 * @description
 */
public class UserPresenterImpl extends BasePresenter<UserContract.IuserView, UserModel>
        implements UserContract.IuserPresenter {

    @Override
    protected UserModel attachModel() {
        return new UserModel();
    }

    @Override
    public void requestUserInfo(String url, WeakHashMap param) {
        getModel()
                .request(url, param, getLifecycleOwner(), (Observer<String>) s -> getView().userInfoReslut(s));
    }

    @Override
    public void requestGetUserInfo(String url, WeakHashMap param) {
        getModel()
                .request(url, param, getLifecycleOwner(), (Observer<String>) s -> getView().getUserInfoResult(s));
    }


}
