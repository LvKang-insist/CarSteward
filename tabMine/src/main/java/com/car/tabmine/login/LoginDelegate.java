package com.car.tabmine.login;

import android.util.Base64;
import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import com.car.core.api.Const;
import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.mvp.mvpdefault.DefaultContract;
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl;
import com.car.core.mvp.view.BaseMvpFragment;
import com.car.core.utils.storage.CarPreference;
import com.car.tabmine.R;
import com.car.tabmine.R2;
import com.hjq.toast.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.WeakHashMap;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.login
 * @time 2019/10/11 22:37
 * @description
 */
@CreatePresenter(DefaultPresenterImpl.class)
public class LoginDelegate extends BaseMvpFragment<DefaultPresenterImpl>
        implements DefaultContract.IDefaultView {

    @BindView(R2.id.toolbar_title)
    AppCompatTextView mToolbarTitle = null;
    @BindView(R2.id.toolbar)
    Toolbar mToolbar = null;
    @BindView(R2.id.login_phone_et)
    AppCompatEditText mLoginPhoneEt = null;
    @BindView(R2.id.login_pass_et)
    AppCompatEditText mLoginPassEt = null;
    @BindView(R2.id.login_btn)
    AppCompatButton mLoginBtn = null;
    @BindView(R2.id.login_forget_password_tv)
    AppCompatTextView mLoginForgetPasswordTv = null;
    @BindView(R2.id.login_go_register_tv)
    AppCompatTextView mLoginGoRegisterTv = null;
    @BindView(R2.id.login_wx_img_login_iv)
    AppCompatImageView mLoginWxImgLoginIv = null;
    @BindView(R2.id.login_qq_img_login_iv)
    AppCompatImageView mLoginQqImgLoginIv = null;

    public static final int NUMBER_LENGTH = 11;
    private final String login = "&a=login";


    @Override
    public Object setLayout() {
        return R.layout.login_delegate;
    }

    @Override
    public int getToolbar() {
        return R.id.toolbar;
    }

    @Override
    public void bindView(View view) {
        mToolbarTitle.setText(R.string.login);
        mToolbarTitle.setTextColor(getResources().getColor(R.color.white));
    }

    @OnClick({R2.id.login_btn,
            R2.id.login_forget_password_tv,
            R2.id.login_go_register_tv,
            R2.id.login_wx_img_login_iv,
            R2.id.login_qq_img_login_iv})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.login_btn) {
            String number = mLoginPhoneEt.getText().toString();
            String pass = mLoginPassEt.getText().toString();

            if (number.isEmpty()){
                ToastUtils.show(getResources().getString(R.string.plase_phone_number));
            }else if (number.length() != NUMBER_LENGTH){
                ToastUtils.show("请输入正确的手机号码");
            }else if (pass.isEmpty()){
                ToastUtils.show(getResources().getString(R.string.plase_password));
            }else {
                //登录
                String logkey = Base64.encodeToString((Base64.encodeToString(number.getBytes(), Base64.URL_SAFE)
                        + "_" + Base64.encodeToString(pass.getBytes(), Base64.URL_SAFE)).getBytes(), Base64.NO_WRAP);
                WeakHashMap<String, Object> param = new WeakHashMap<>();
                param.put("loginKey",logkey);
                getPresenter().request(Const.API_BASE_USER+login,param);
            }

        } else if (i == R.id.login_forget_password_tv) {

        } else if (i == R.id.login_go_register_tv) {
            //注册
            fragmentAnimStart(new SignUpDelegate());
        } else if (i == R.id.login_wx_img_login_iv) {

        } else if (i == R.id.login_qq_img_login_iv) {
        }
    }

    @Override
    public void onResult( String result) {
        LogInBean logInBean = gson.fromJson(result, LogInBean.class);
        if (logInBean== null){
            ToastUtils.show("请求出错");
        }else {
            if (logInBean.getStatus() == 1){
                CarPreference.putLogin(true);
                CarPreference.putTokenId(logInBean.getData().getTokenId());
                CarPreference.putLoginName(logInBean.getData().getLoginName());
                CarPreference.putUserName(logInBean.getData().getUserName());
                CarPreference.putUserSex(logInBean.getData().getUserSex());
                CarPreference.putUserPhoto(logInBean.getData().getUserPhoto());
                CarPreference.putQq(logInBean.getData().getIsBindingQQ());
                CarPreference.putWechat(logInBean.getData().getIsBindingWX());
                CarPreference.putHomeIsRevise(true);
                CarPreference.putUserInfoIsRevise(true);
                EventBus.getDefault().post(logInBean);
                fragmentAnimBack();
            }
        }
    }
}
