package com.car.tabmine.login;

import android.app.ProgressDialog;
import android.view.Gravity;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.mvp.mvpdefault.DefaultContract;
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl;
import com.car.core.mvp.view.BaseMvpFragment;
import com.car.core.ui.dialog.DialogBuilder;
import com.car.core.ui.dialog.ToastDialog;
import com.car.tabmine.R;
import com.car.tabmine.R2;
import com.hjq.toast.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.login
 * @time 2019/10/14 20:48
 * @description
 */

@CreatePresenter(DefaultPresenterImpl.class)
public class SignUpDelegate extends BaseMvpFragment<DefaultPresenterImpl>
        implements DefaultContract.IDefaultView {

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

    @OnClick({R2.id.signup_get_code_btn,
            R2.id.signup_is_check,
            R2.id.signup_xieyi_tv,
            R2.id.mine_register_btn})

    void onClick(View view) {
        int id = view.getId();
        if (id == R.id.signup_get_code_btn) {
            if (mPhoneCodeEt.getText().toString().length() != 11) {
                ToastUtils.show(getResources().getString(R.string.plase_success_phone_number));
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

                showLoading("正在注册...");

             /*   dialog1 = new ProgressDialog(this);
                dialog1.setCancelable(false);
                dialog1.setMessage("正在注册...");
                dialog1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog1.show();
                doRegister();
//                    提交验证码  在eventHandler里面查看验证结果
//                    SMSSDK.submitVerificationCode("86", et_usertel.getText().toString().trim(), et_code.getText().toString().trim());
//                    checkSMS.smsVerfy = et_code.getText().toString().trim();
//                    checkSMS.userPhone = et_usertel.getText().toString().trim();
//                    requestNoDialog(API_BASE_URL_PUBLIC, checkSMS);*/

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
    public void onResult(boolean flag, String result) {

    }
}
