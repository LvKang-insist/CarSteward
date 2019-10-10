package com.car.tabmine;

import android.view.View;
import android.widget.GridView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.car.core.delegate.BottomItemDelegate;
import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.mvp.mvpdefault.DefaultContract;
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl;
import com.car.tabmine.adapter.GradViewThreeAdapter;
import com.car.tabmine.mvp.FunctionItemBean;
import com.car.tabmine.mvp.MineContract;
import com.car.tabmine.mvp.MinePresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.badgeview.BGABadgeImageView;
import cn.bingoogolapple.badgeview.BGABadgeLinearLayout;
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
    @BindView(R2.id.mine_account_number_tv)
    AppCompatTextView mNumber = null;
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
        } else if (id == R.id.mine_account_vip_iv) {
        } else if (id == R.id.mine_sign_tv) {
        }
    }


    @Override
    public Object setLayout() {
        return R.layout.mine_delegate;
    }

    @Override
    public void bindView(View view) {
        getPresenter().getGvThreeData();
    }

    @Override
    public int getToolbar() {
        return R.id.mine_toolbar;
    }

    @Override
    public void onResult(boolean flag, String result) {

    }

    @Override
    public void setGvThree(List<FunctionItemBean> list) {
        GradViewThreeAdapter adapter = new GradViewThreeAdapter(list, getContext(), R.layout.mine_item_icon_tv);
        mGridViewThree.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
