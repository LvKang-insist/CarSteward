package com.car.tabmine.login.signmvp;

import com.car.core.mvp.presenter.IBasePresenter;
import com.car.core.mvp.view.BaseMvpFragment;
import com.car.core.mvp.view.IBaseView;

import java.util.WeakHashMap;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.login.signmvp
 * @time 2019/10/16 20:28
 * @description
 */
public class SignUpContract {


    public interface IsignUpView extends IBaseView {
        /**
         *  验证手机号的结果
         * @param isCheck true 为成功
         */
        void checkNumberResult(boolean isCheck);

        /**
         * 验证码
         * @param code
         */
        void smsResult(String code);

        /**
         * 注册结果
         * @param result
         */
        void signUpResult(String result);
    }

    public interface IsingUpPresenter extends IBasePresenter<IsignUpView>{
        /**
         * 请求验证手机号
         * @param mvpFragment
         * @param url
         * @param param
         */
        void requestNumberCheck(BaseMvpFragment mvpFragment, String url, WeakHashMap param);

        /**
         * 发送短信
         * @param mvpFragment
         * @param url
         * @param param
         */
        void sendSms(BaseMvpFragment mvpFragment, String url, WeakHashMap param);

        /**
         * 注册
         * @param mvpFragment
         * @param url
         * @param param
         */
        void signUp(BaseMvpFragment mvpFragment, String url, WeakHashMap param);
    }

}
