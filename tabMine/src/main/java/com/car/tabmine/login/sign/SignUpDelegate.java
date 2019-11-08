package com.car.tabmine.login.sign;

import android.graphics.Color;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.car.core.api.Const;
import com.car.core.latte.Latte;
import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.mvp.view.BaseMvpDelegate;
import com.car.core.ui.dialog.BaseFragDialog;
import com.car.core.utils.time.SetTelCountTimer;
import com.car.tabmine.R;
import com.car.tabmine.R2;
import com.car.core.utils.bean.SignUpBean;
import com.car.tabmine.login.sign.signmvp.SignUpContract;
import com.car.tabmine.login.sign.signmvp.SignUpPresenterImpl;
import com.car.tabmine.xieyi.XieYIDelegate;
import com.elvishew.xlog.XLog;
import com.hjq.toast.ToastUtils;

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
public class SignUpDelegate extends BaseMvpDelegate<SignUpPresenterImpl>
        implements SignUpContract.IsignUpView {

    @BindView(R2.id.toolbar_title)
    AppCompatTextView mToolbarTitle = null;
    @BindView(R2.id.toolbar)
    Toolbar mToolbar = null;
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
                        .requestNumberCheck(Const.API_BASE_URL_PUBLIC + checkNumber, map);
            }
        } else if (id == R.id.signup_xieyi_tv) {
            getSupportDelegate().start(new XieYIDelegate());
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
                getPresenter().signUp(Const.API_BASE_USER + signUp, maps);
                Latte.showLoading("注册中");
            }
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_signup;
    }

    @Override
    public void bindView(View view) {
        mToolbar.setBackgroundColor(Color.TRANSPARENT);
        mToolbarTitle.setText(R.string.login);
        mToolbarTitle.setTextColor(getResources().getColor(R.color.white));
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
                        getPresenter()
                                .sendSms(Const.API_BASE_URL_PUBLIC + sendSMS, map);
                        dialog.dismiss();
                    })
                    .show(getChildFragmentManager(), "send_sms");
        }
    }

    @Override
    public void smsResult(String code) {
        JSONObject object = JSON.parseObject(code);
        if (object.getInteger("status") == 1) {
            SetTelCountTimer telCountTimer = new SetTelCountTimer(mCodeBtn);
            telCountTimer.start();
            mCodeBtn.setEnabled(false);
        }
        ToastUtils.show(object.getString("msg"));
    }

    @Override
    public void signUpResult(String result) {
        XLog.e(result);
        SignUpBean signUpBean = gson.fromJson(result, SignUpBean.class);
        Latte.stopLoading();
        if (signUpBean.getStatus() != 1) {
            ToastUtils.show(signUpBean.getMsg());
        } else if (signUpBean.getStatus() == 1) {
            ToastUtils.show(signUpBean.getMsg());
            fragmentAnimBack();
        }

    }
}
