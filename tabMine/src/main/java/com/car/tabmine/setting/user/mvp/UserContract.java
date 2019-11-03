package com.car.tabmine.setting.user.mvp;

import com.car.core.mvp.presenter.IBasePresenter;
import com.car.core.mvp.view.IBaseView;
import com.car.tabmine.login.sign.signmvp.SignUpContract;

import java.util.WeakHashMap;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.setting.user.mvp
 * @time 2019/11/3 16:13
 * @description
 */
public class UserContract {

    public interface IuserView extends IBaseView {
        /**
         * 设置个人资料
         *
         * @param result 设置结果
         */
        void userInfoReslut(String result);

        void getUserInfoResult(String reslut);

    }

    public interface IuserPresenter extends IBasePresenter<IuserView> {
        /**
         * 请求修改个人资料
         *
         * @param url
         * @param param
         */
        void requestUserInfo(String url, WeakHashMap param);

        /**
         * 获取个人资料
         *
         * @param url
         * @param param
         */
        void requestGetUserInfo(String url, WeakHashMap param);
    }
}
