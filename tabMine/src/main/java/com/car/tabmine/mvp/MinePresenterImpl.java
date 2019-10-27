package com.car.tabmine.mvp;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.car.core.latte.Latte;
import com.car.core.mvp.presenter.BasePresenter;
import com.car.tabmine.MineDelegate;
import com.elvishew.xlog.XLog;

import java.util.WeakHashMap;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.mvp
 * @time 2019/10/10 22:38
 * @description
 */
public class MinePresenterImpl extends BasePresenter<MineContract.IMineView, MineModel>
        implements MineContract.IMinePresenter {

    private UserCenterBean mCenterBean;

    @Override
    protected MineModel attachModel() {
        return new MineModel();
    }

    @Override
    public void request(String url, WeakHashMap param) {
        getModel().request(url, param, getLifecycleOwner(), (Observer<String>) s -> getView().onResult(s));
    }

    @Override
    public void requestUserCenter(String url, WeakHashMap param) {
        getModel().request(url, param, getLifecycleOwner(), (Observer<String>) s -> {
            XLog.json(s);
            mCenterBean = Latte.getGson().fromJson(s, UserCenterBean.class);
            getView().onUserCenter(mCenterBean);
            getGvOneData();
            getGvTwoData();
            getGvThreeData();
        });
    }

    public void getGvOneData() {
        if (mCenterBean != null) {
            String[] count = new String[]{
                    mCenterBean.getFavoritesCount(),
                    mCenterBean.getBrowsHistoryCount(),
                    mCenterBean.getBankCardCount(),
                    mCenterBean.getCouponsCount()};
            getView().setGvOne(getModel().setGvOneData(count));
        } else {
            String[] count = new String[]{"0", "0", "0", "0"};
            getView().setGvOne(getModel().setGvOneData(count));
        }
    }

    public void getGvTwoData() {
        getView().setGvTwo(getModel().setGvTwoData());
    }

    public void getGvThreeData() {
        getView().setGvThree(getModel().setGvThreeData());
    }


}
