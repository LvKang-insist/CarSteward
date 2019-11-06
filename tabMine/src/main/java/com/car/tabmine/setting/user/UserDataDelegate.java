package com.car.tabmine.setting.user;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.PermissionRequest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.car.core.api.BaseUrl;
import com.car.core.api.Const;
import com.car.core.latte.Latte;
import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.mvp.mvpdefault.DefaultContract;
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl;
import com.car.core.mvp.view.BaseMvpFragment;
import com.car.core.utils.bean.GetUserInfoBean;
import com.car.core.utils.storage.CarPreference;
import com.car.core.utils.util.GlideUtil;
import com.car.core.utils.util.RequestParam;
import com.car.core.utils.util.UrlParam;
import com.car.tabmine.R;
import com.car.tabmine.R2;
import com.car.tabmine.setting.user.mvp.UserContract;
import com.car.tabmine.setting.user.mvp.UserPresenterImpl;
import com.elvishew.xlog.XLog;
import com.hjq.toast.ToastUtils;
import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.setting.user
 * @time 2019/11/3 14:05
 * @description 个人资料
 */
@CreatePresenter(UserPresenterImpl.class)
public class UserDataDelegate extends BaseMvpFragment<UserPresenterImpl>
        implements UserContract.IuserView {

    @BindView(R2.id.toolbar_title)
    AppCompatTextView mTitle = null;
    @BindView(R2.id.delegate_user_head_photo)
    CircleImageView mCircle = null;
    @BindView(R2.id.delegate_user_data_phone_number)
    AppCompatTextView mPhoneNumber = null;
    @BindView(R2.id.delegate_user_data_number)
    AppCompatTextView mNumber = null;
    @BindView(R2.id.delegate_user_data_name)
    AppCompatEditText mName = null;
    @BindView(R2.id.delegate_user_data_man)
    AppCompatRadioButton mMan = null;
    @BindView(R2.id.delegate_user_data_woman)
    AppCompatRadioButton mWoMan = null;

    @OnClick({R2.id.delegate_user_data_save_btn,
            R2.id.delegate_user_data_photo_rlayout})
    void onClick(View view) {
        final int id = view.getId();
        if (id == R.id.delegate_user_data_save_btn) {
            upUserData();
        } else if (id == R.id.delegate_user_data_photo_rlayout) {
            startCameraWithCheck();
        }
    }


    private void upUserData() {
        String name = mName.getText().toString().trim();
        if (name.isEmpty()) {
            ToastUtils.show("请输入昵称!");
        } else {
            if (mMan.isChecked() || mWoMan.isChecked()) {
                String sex;
                if (mMan.isChecked()) {
                    sex = "1";
                } else if (mWoMan.isChecked()) {
                    sex = "2";
                } else {
                    sex = "0";
                }
                Latte.showLoading("");
                getPresenter().requestUserInfo(Const.API_BASE_USER + UrlParam.getParam("userInfo"),
                        RequestParam.builder()
                                .addTokenId()
                                .addParam("userName", name)
                                .addParam("userSex", sex)
                                .build());
            }
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_user_data;
    }

    @Override
    public void bindView(View view) {
        mTitle.setText("个人资料");
        mTitle.setTextColor(Color.WHITE);
        init();
    }

    private void init() {
        GlideUtil.setImage(BaseUrl.BASE_URL + CarPreference.getUserPhoto(), mCircle);
        mPhoneNumber.setText(CarPreference.getUserPhone());
        mNumber.setText(CarPreference.getLoginName());
        mName.setText(CarPreference.getUserName());
        if (CarPreference.getUserSex().equals(getString(R.string.man))) {
            mMan.setChecked(true);
        } else if (CarPreference.getUserSex().equals(getString(R.string.woman))) {
            mWoMan.setChecked(true);
        }
    }

    @Override
    public int getToolbar() {
        return R.id.toolbar;
    }


    @Override
    public void userInfoReslut(String result) {
        JSONObject object = JSON.parseObject(result);
        ToastUtils.show(object.getString("msg"));
        Latte.stopLoading();
        CarPreference.putUserInfoIsRevise(true);
        getPresenter().requestGetUserInfo(Const.API_BASE_URL_PUBLIC + UrlParam.getParam("getUserInfo"),
                RequestParam.builder().addTokenId().build());
    }

    @Override
    public void getUserInfoResult(String reslut) {
        GetUserInfoBean bean = gson.fromJson(reslut, GetUserInfoBean.class);
        CarPreference.putUserSex(bean.getData().getUserSex());
        CarPreference.putUserName(bean.getData().getUserName());
        CarPreference.putUserInfoIsRevise(true);
        init();
    }
}
