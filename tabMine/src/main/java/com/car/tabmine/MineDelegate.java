package com.car.tabmine;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.car.core.api.BaseUrl;
import com.car.core.api.Const;
import com.car.core.delegate.BottomItemDelegate;
import com.car.core.latte.Latte;
import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.utils.storage.CarPreference;
import com.car.core.utils.util.GlideUtil;
import com.car.core.utils.util.RequestParam;
import com.car.core.utils.bean.LogInBean;
import com.car.tabmine.login.LoginDelegate;
import com.car.tabmine.mine.adapter.GradViewOneAdapter;
import com.car.tabmine.mine.adapter.GradViewThreeAdapter;
import com.car.tabmine.mine.adapter.GradViewTwoAdapter;
import com.car.core.utils.bean.GetUserInfoBean;
import com.car.tabmine.mine.mvp.MineContract;
import com.car.tabmine.mine.mvp.MinePresenterImpl;
import com.car.core.utils.bean.TextImageBean;
import com.car.core.utils.bean.TextStringBean;
import com.car.core.utils.bean.GetUserCenterBean;
import com.car.tabmine.setting.SettingDelegate;
import com.hjq.toast.ToastUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.badgeview.BGABadgeImageView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine
 * @time 2019/10/7 22:57
 * @description
 */
@CreatePresenter(MinePresenterImpl.class)
public class MineDelegate extends BottomItemDelegate<MinePresenterImpl>
        implements MineContract.IMineView {

    @BindView(R2.id.mine_news_bgab_iv)
    BGABadgeImageView mNewsIv = null;
    @BindView(R2.id.mine_head_circle_iv)
    CircleImageView mHeadCircle = null;
    @BindView(R2.id.mine_account_name_tv)
    AppCompatTextView mName = null;
    @BindView(R2.id.mine_account_vip_iv)
    AppCompatImageView mVip = null;
    @BindView(R2.id.mine_sign_tv)
    AppCompatTextView mSign = null;
    @BindView(R2.id.mine_gv_one)
    GridView mGridViewOne = null;
    @BindView(R2.id.mine_gv_two)
    GridView mGridViewTwo = null;
    @BindView(R2.id.mine_gv_three)
    GridView mGridViewThree = null;
    private GradViewOneAdapter mOneAdapter;
    private GradViewTwoAdapter mTowAdapter;
    private GradViewThreeAdapter mThreeAdapter;
    private final String mUserInfo = "&a=getUserInfo";
    private final String mUerCenter = "&a=userCenter";
    private final String mUserSignIn = "&a=userSignIn";

    @OnClick({R2.id.min_setting_iv,
            R2.id.mine_news_bgab_iv,
            R2.id.mine_head_circle_iv,
            R2.id.mine_account_vip_iv,
            R2.id.mine_sign_tv})
    void onClick(View view) {
        final int id = view.getId();
        if (!CarPreference.getLogin()) {
            //登录
            parentfragmentAnimStart(new LoginDelegate());
        } else {
            if (id == R.id.min_setting_iv) {
                //设置
                parentfragmentAnimStart(new SettingDelegate());
            } else if (id == R.id.mine_news_bgab_iv) {

            } else if (id == R.id.mine_head_circle_iv) {

            } else if (id == R.id.mine_account_vip_iv) {

            } else if (id == R.id.mine_sign_tv) {
                //签到
                getPresenter().requestSignIn(Const.API_BASE_USER + mUserSignIn,
                        RequestParam.builder()
                                .addTokenId()
                                .build());
            }
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_mine;
    }

    @Override
    public void bindView(View view) {
        getPresenter().getGvOneData();
        getPresenter().getGvTwoData();
        getPresenter().getGvThreeData();
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        //刷新数据
        refreshView();
    }

    @Override
    public void onSupportVisible() {
        if (CarPreference.isUserInfoIsRevise()) {
            refreshView();
            CarPreference.putUserInfoIsRevise(false);
        }
    }

    @Override
    public int getToolbar() {
        return R.id.mine_toolbar;
    }

    @Override
    public boolean isEventBus() {
        return true;
    }

    @Override
    public void onResult(String result) {
        GetUserInfoBean getUserInfoBean = gson.fromJson(result, GetUserInfoBean.class);
        if (getUserInfoBean.getStatus() == 1) {
            GetUserInfoBean.DataBean data = getUserInfoBean.getData();
            CarPreference.putUserName(data.getUserName());
            CarPreference.putUserSex(data.getUserSex());
            CarPreference.putLoginName(data.getLoginName());
            CarPreference.putUserPhone(data.getUserPhone());
            CarPreference.putUserMoney(data.getUserMoney());
            CarPreference.putUserIsPayPass(data.getPayPwd());
            CarPreference.putUserCashBackMoney(data.getUserCashBackMoney());
            CarPreference.putCashStartMoney(data.getCashStartMoney());
            CarPreference.putCashEndMoney(data.getCashEndMoney());
            CarPreference.putCashRate(data.getCashRate());
            if (data.getUserPhoto() != null) {
                CarPreference.putUserPhoto(data.getUserPhoto());
            }
            if (data.getUserName() != null) {
                if (!data.getUserName().isEmpty()) {
                    mName.setText(data.getUserName());
                } else {
                    mName.setText(data.getLoginName());
                }
            } else {
                mName.setText(data.getLoginName());
            }
            if (data.getUserPhoto() != null && !data.getUserPhoto().isEmpty()) {
                GlideUtil.setImage(BaseUrl.BASE_URL + data.getUserPhoto(), mHeadCircle);
            }
            //userCenter
            WeakHashMap<String, Object> build = RequestParam.builder()
                    .addTokenId()
                    .build();
            getPresenter().requestUserCenter(Const.API_BASE_USER + mUerCenter, build);
        } else {
            Latte.stopLoading();
            ToastUtils.show(getUserInfoBean.getMsg());
        }
    }

    @Override
    public void onUserCenter(GetUserCenterBean centerBean) {
        //会员
        GlideUtil.setImage(BaseUrl.BASE_URL + centerBean.getUserInfo().getRankIcon_1(), mVip);
        //消息数量
        int count = Integer.parseInt(centerBean.getMsgCount());
        if (count > 0) {
            mNewsIv.showTextBadge(centerBean.getMsgCount());
        } else {
            mNewsIv.hiddenBadge();
        }
        //通知
        if (centerBean.getAffiche().size() == 0) {

        } else {

        }
        Latte.stopLoading();
    }


    private void refreshView() {
        if (CarPreference.getLogin()) {
            WeakHashMap map = new WeakHashMap();
            map.put("tokenId", CarPreference.getTokenId());
            Latte.showLoading("");
            getPresenter().request(Const.API_BASE_URL_PUBLIC + mUserInfo, map);
        } else {
            mName.setText("请登录");
            mHeadCircle.setImageResource(R.drawable.head_photo);
            mOneAdapter.clear();
            mTowAdapter.clear();
        }
    }

    @Override
    public void setGvOne(List<TextStringBean> list) {
        if (mOneAdapter == null) {
            mOneAdapter = new GradViewOneAdapter(list, getActivity(), R.layout.item_tv_tv);
            mGridViewOne.setAdapter(mOneAdapter);
        } else {
            mOneAdapter.addData(list);
        }
    }

    @Override
    public void setGvTwo(List<TextImageBean> list) {
        if (mTowAdapter == null) {
            mTowAdapter = new GradViewTwoAdapter(list, getActivity(), R.layout.item_icon_tv);
            mGridViewTwo.setAdapter(mTowAdapter);
        } else {
            mTowAdapter.addData(list);
        }
    }

    @Override
    public void setGvThree(List<TextImageBean> list) {
        mThreeAdapter = new GradViewThreeAdapter(list, R.layout.item_icon_tv, this);
        mGridViewThree.setAdapter(mThreeAdapter);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void loginResult(LogInBean bean) {
        ToastUtils.show(getResources().getString(R.string.login_success));
        if (bean.getData().getUserName() != null) {
            mName.setText(bean.getData().getUserName());
        } else {
            mName.setText(bean.getData().getLoginName());
        }
        if (bean.getData().getUserPhoto() != null && !bean.getData().getUserPhoto().isEmpty()) {
            Glide.with(this)
                    .load(BaseUrl.BASE_URL + bean.getData().getUserPhoto())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mHeadCircle);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
