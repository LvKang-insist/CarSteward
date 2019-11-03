package com.car.core.net.lvdata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.car.core.latte.Latte;
import com.car.core.mvp.view.BaseMvpFragment;
import com.car.core.utils.storage.CarPreference;
import com.hjq.toast.ToastUtils;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.core.net.lvdata
 * @time 2019/10/25 21:51
 * @description
 */
public class VerifyResult {
    public static boolean startVerify(String reslut) {
        if (reslut != null && reslut.indexOf("{") > -1) {
            JSONObject object = JSON.parseObject(reslut);
            if (object.getInteger("status") == 1) {
                return true;
            } else if (object.getInteger("status") == -1000) {
                ToastUtils.show("用户令牌已过期，请重新登录");
                reLogin();
                return false;
            } else {
                return true;
            }
        } else {
            ToastUtils.show("请求出错，请重试！");
            return false;
        }
    }

    private static void reLogin() {
        CarPreference.putTokenId(null);
        CarPreference.putLogin(false);
        CarPreference.putUserSex(String.valueOf(0));
        CarPreference.putLoginName(null);
        CarPreference.putUserName(null);
        CarPreference.putUserPhoto(null);
        CarPreference.putUserPhone(null);
        CarPreference.putUserInfoIsRevise(true);
        CarPreference.putTokenId(null);
        try {
            Class<?> aClass = Class.forName("com.car.tabmine.login.LoginDelegate");
            if (aClass != null) {
                Latte.getBaseMvpActivity().getSupportDelegate().start((BaseMvpFragment) aClass.newInstance());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            Latte.stopLoading();
        }
    }
}
