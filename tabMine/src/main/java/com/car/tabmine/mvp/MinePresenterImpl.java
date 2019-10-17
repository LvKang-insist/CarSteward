package com.car.tabmine.mvp;

import androidx.lifecycle.ViewModelProvider;

import com.car.core.mvp.model.BaseModel;
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl;
import com.car.core.mvp.presenter.BasePresenter;
import com.car.core.mvp.view.BaseMvpFragment;

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

    @Override
    public void request(BaseMvpFragment mvpFragment, String url, WeakHashMap param) {

    }

    public void getOneTwoData() {
        getView().setGvOne(getModel(MineModel.class).setGvOneData());
    }

    public void getGvTwoData() {
        getView().setGvTwo(getModel(MineModel.class).setGvTwoData());
    }

    public void getGvThreeData() {
        getView().setGvThree(getModel(MineModel.class).setGvThreeData());
    }

}
