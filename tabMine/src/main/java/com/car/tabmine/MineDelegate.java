package com.car.tabmine;

import android.view.View;
import android.widget.GridView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.car.core.api.BaseUrl;
import com.car.core.delegate.BottomItemDelegate;
import com.car.core.mvp.factory.CreatePresenter;
import com.car.tabmine.LiveDataRetrofit.test.TestViewModel;
import com.car.tabmine.adapter.GradViewOneAdapter;
import com.car.tabmine.adapter.GradViewThreeAdapter;
import com.car.tabmine.adapter.GradViewTwoAdapter;
import com.car.tabmine.login.LogInBean;
import com.car.tabmine.login.LoginDelegate;
import com.car.tabmine.mvp.TextImageBean;
import com.car.tabmine.mvp.MineContract;
import com.car.tabmine.mvp.MinePresenterImpl;
import com.car.tabmine.mvp.TextIntegerBean;
import com.hjq.toast.ToastUtils;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

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

    @OnClick({R2.id.min_setting_iv,
            R2.id.mine_news_bgab_iv,
            R2.id.mine_head_circle_iv,
            R2.id.mine_account_vip_iv,
            R2.id.mine_sign_tv})
    void onClick(View view) {
        final int id = view.getId();
        if (id == R.id.min_setting_iv) {
        } else if (id == R.id.mine_news_bgab_iv) {
        } else if (id == R.id.mine_head_circle_iv) {
            //登录
//            parentfragmentAnimStart(new LoginDelegate());


            TestViewModel model = new TestViewModel();
            model.onCreate(this);
            model.getBaidu();

        } else if (id == R.id.mine_account_vip_iv) {
            Logger.e("hellow");
        } else if (id == R.id.mine_sign_tv) {
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_mine;
    }

    @Override
    public void bindView(View view) {
        getPresenter().getOneTwoData();
        getPresenter().getGvTwoData();
        getPresenter().getGvThreeData();
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
    public void onResult(boolean flag, String result) {

    }

    @Override
    public void setGvOne(List<TextIntegerBean> list) {
        GradViewOneAdapter adapter = new GradViewOneAdapter(list, getActivity(), R.layout.mine_item_tv_tv);
//        Adap adapter = new Adap(list,getActivity(),R.layout.mine_item_tv_tv);
        mGridViewOne.setAdapter(adapter);
    }

    @Override
    public void setGvTwo(List<TextImageBean> list) {
        GradViewTwoAdapter adapter = new GradViewTwoAdapter(list, getActivity(), R.layout.mine_item_icon_tv);
        mGridViewTwo.setAdapter(adapter);
    }

    @Override
    public void setGvThree(List<TextImageBean> list) {
        GradViewThreeAdapter adapter = new GradViewThreeAdapter(list, getActivity(), R.layout.mine_item_icon_tv);
        mGridViewThree.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void loginResult(LogInBean bean) {
        ToastUtils.show(getResources().getString(R.string.login_success));
        if (bean.getData().getUserName() != null) {
            mName.setText(bean.getData().getUserName());
        } else {
            mName.setText(bean.getData().getLoginName());
        }
        if (bean.getData().getUserPhoto() != null && !bean.getData().getUserPhoto().isEmpty()){
            Glide.with(this)
                    .load(BaseUrl.BASE_URL+bean.getData().getUserPhoto())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mHeadCircle);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
