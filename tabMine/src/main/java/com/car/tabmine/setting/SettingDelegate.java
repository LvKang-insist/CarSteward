package com.car.tabmine.setting;

import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl;
import com.car.core.mvp.view.BaseMvpDelegate;
import com.car.core.utils.storage.CarPreference;
import com.car.tabmine.R;
import com.car.tabmine.R2;
import com.car.tabmine.setting.user.UserDataDelegate;
import com.car.ui.recycler.rdefault.ListAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.setting
 * @time 2019/10/28 20:48
 * @description
 */
@CreatePresenter(DefaultPresenterImpl.class)
public class SettingDelegate extends BaseMvpDelegate<DefaultPresenterImpl> implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R2.id.toolbar_title)
    AppCompatTextView mToolbarTitle = null;
    @BindView(R2.id.toolbar)
    Toolbar mToolbar = null;
    @BindView(R2.id.delegate_setting_recycler)
    RecyclerView mRecycler = null;

    @OnClick(R2.id.delegate_setting_back_login)
    void onBackLogin() {
        CarPreference.putTokenId(null);
        CarPreference.putLogin(false);
        CarPreference.putUserSex(null);
        CarPreference.putLoginName(null);
        CarPreference.putUserName(null);
        CarPreference.putUserPhoto(null);
        CarPreference.putUserPhone(null);
//        CarPreference.putMyCar(getApplication(), null);
        CarPreference.putQq("0");
        CarPreference.putWechat("0");
        CarPreference.putUserInfoIsRevise(true);
        CarPreference.putHomeIsRevise(true);
        fragmentAnimBack();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_setting;
    }

    @Override
    public void bindView(View view) {
        mToolbarTitle.setText(R.string.setting);
        mToolbarTitle.setTextColor(getResources().getColor(R.color.white));

        RvConverter converter = new RvConverter();
        ListAdapter adapter = new ListAdapter(converter.convert());
        adapter.setOnItemClickListener(this);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.setAdapter(adapter);
    }

    @Override
    public int getToolbar() {
        return R.id.toolbar;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (position == 0) {
            fragmentAnimStart(new UserDataDelegate());
        }
    }
}
