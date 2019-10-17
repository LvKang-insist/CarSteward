package com.car.tabmine.login;

import android.app.ProgressDialog;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.car.core.api.BaseUrl;
import com.car.core.api.Const;
import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.mvp.mvpdefault.DefaultContract;
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl;
import com.car.core.mvp.view.BaseMvpFragment;
import com.car.core.net.interceptors.InterceptorsManage;
import com.car.core.ui.dialog.BaseFragDialog;
import com.car.core.ui.dialog.DialogBuilder;
import com.car.core.ui.dialog.ToastDialog;
import com.car.core.utils.storage.CarPreference;
import com.car.core.utils.time.SetTelCountTimer;
import com.car.tabmine.R;
import com.car.tabmine.R2;
import com.car.tabmine.login.signmvp.SignUpBean;
import com.car.tabmine.login.signmvp.SignUpContract;
import com.car.tabmine.login.signmvp.SignUpPresenterImpl;
import com.hjq.toast.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.login
 * @time 2019/10/14 20:48
 * @description
 */

@CreatePresenter(SignUpPresenterImpl.class)
public class SignUpDelegate extends BaseMvpFragment<SignUpPresenterImpl>
        implements SignUpContract.IsignUpView {

    @BindView(R2.id.signup_user_phone_et)
    AppCompatEditText mPhoneEt = null;
    @BindView(R2.id.signup_get_code_btn)
    AppCompatButton mCodeBtn = null;
    @BindView(R2.id.signup_phone_code_et)
    AppCompatEditText mPhoneCodeEt = null;
    @BindView(R2.id.signup_pasword_et)
    AppCompatEditText mPassEt = null;
    @BindView(R2.id.signup_consent_password_et)
    AppCompatEditText mPassSecondEt = null;
    @BindView(R2.id.signup_is_check)
    AppCompatCheckBox mIsCheck = null;
    @BindView(R2.id.signup_xieyi_tv)
    AppCompatTextView mXieYiTv = null;
    @BindView(R2.id.mine_register_btn)
    AppCompatButton mRegisterBtn = null;

    private final String checkNumber = "&a=checkUserPhone";
    private final String sendSMS = "&a=SMSS";
    private final String signUp = "&a=register";

    @OnClick({R2.id.signup_get_code_btn,
            R2.id.signup_is_check,
            R2.id.signup_xieyi_tv,
            R2.id.mine_register_btn})
    void onClick(View view) {
        int id = view.getId();
        if (id == R.id.signup_get_code_btn) {
            if (mPhoneEt.getText().toString().length() != 11) {
                ToastUtils.show(getResources().getString(R.string.plase_success_phone_number));
            } else {
                //检测手机号是否被使用
                WeakHashMap<String, Object> map = new WeakHashMap<>();
                map.put("userPhone", mPhoneEt.getText().toString());
                getPresenter()
                        .requestNumberCheck(this,
                                Const.API_BASE_URL_PUBLIC + checkNumber, map);
            }
        } else if (id == R.id.signup_is_check) {

        } else if (id == R.id.signup_xieyi_tv) {

        } else if (id == R.id.mine_register_btn) {
            if (mPhoneCodeEt.getText().toString().trim().isEmpty()) {
                ToastUtils.show("请填写验证码");
            } else if (!mPassEt.getText().toString().equals(mPassSecondEt.getText().toString())) {
                ToastUtils.show("密码不一致");
            } else if (!mIsCheck.isChecked()) {
                ToastUtils.show("注册需同意用户协议！");
            } else {
                String number = mPhoneEt.getText().toString();
                String pass = mPassEt.getText().toString();
                String signkey = Base64.encodeToString((Base64.encodeToString(number.getBytes(), Base64.URL_SAFE)
                        + "_" + Base64.encodeToString(pass.getBytes(), Base64.URL_SAFE)).getBytes(), Base64.NO_WRAP);
                String smsVerfy = mPhoneCodeEt.getText().toString();
                WeakHashMap<String, Object> maps = new WeakHashMap<>();
                maps.put("loginKey", signkey);
                maps.put("smsVerfy", smsVerfy);
                InterceptorsManage.IS_UP_COOKIE_INTERCEPTOR = true;
                getPresenter().signUp(this, Const.API_BASE_USER + signUp, maps);
                showLoading("注册中");
            }
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.signup_delegate;
    }

    @Override
    public void bindView(View view) {

    }

    @Override
    public void checkNumberResult(boolean isCheck) {
        if (isCheck) {
            BaseFragDialog.Builder()
                    .setContentView(R.layout.dialog)
                    .setGravity(Gravity.CENTER)
                    .setCancelable(false)
                    .build()
                    .setText(R.id.tv_dialog_message, "发送短信到这个号码：\n" + mPhoneEt.getText().toString())
                    .setListener(R.id.tv_dialog_cancel, (dialog, view) -> {
                        dialog.dismiss();
                    })
                    .setListener(R.id.tv_dialog_confirm, (dialog, view) -> {
                        //获取验证码
                        WeakHashMap<String, Object> map = new WeakHashMap<>();
                        map.put("userPhone", mPhoneEt.getText().toString());
                        InterceptorsManage.IS_OBTAIN_COOKIE_INTERCEPTOR = true;
                        getPresenter()
                                .sendSms(this,
                                        Const.API_BASE_URL_PUBLIC + sendSMS, map);
                        dialog.dismiss();
                    })
                    .show(getChildFragmentManager(), "send_sms");
        }
    }

    @Override
    public void smsResult(String code) {
        InterceptorsManage.IS_OBTAIN_COOKIE_INTERCEPTOR = false;
        JSONObject object = JSON.parseObject(code);
        Log.e("_____", "smsResult: "+code );
        if (object.getInteger("status")== 1){
            SetTelCountTimer telCountTimer = new SetTelCountTimer(mCodeBtn);
            telCountTimer.start();
            mCodeBtn.setEnabled(false);
        }
        ToastUtils.show(object.getString("msg"));
    }

    @Override
    public void signUpResult(String result) {
        InterceptorsManage.IS_UP_COOKIE_INTERCEPTOR = false;
        SignUpBean signUpBean = gson.fromJson(result, SignUpBean.class);
        stopLoading();
        if (signUpBean.getStatus() != 1) {
            ToastUtils.show(signUpBean.getMsg());
        } else if (signUpBean.getStatus() == 1) {
            ToastUtils.show(signUpBean.getMsg());
            CarPreference.putLogin(true);
            CarPreference.putTokenId(signUpBean.getData().getTokenId());
            CarPreference.putLoginName(signUpBean.getData().getLoginName());
            CarPreference.putUserName(signUpBean.getData().getUserName());
            CarPreference.putUserSex(signUpBean.getData().getUserSex());
            CarPreference.putUserPhoto(signUpBean.getData().getUserPhoto());
            CarPreference.putQq(signUpBean.getData().getIsBindingQQ());
            CarPreference.putWechat(signUpBean.getData().getIsBindingWX());
            CarPreference.putHomeIsRevise(true);
            CarPreference.putUserInfoIsRevise(true);
            EventBus.getDefault().post(signUpBean);
            fragmentAnimBack();
        }
//        stopLoading();
    }
}
