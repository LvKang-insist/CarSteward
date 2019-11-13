package com.car.tabhome.home.mvp;

import androidx.lifecycle.Observer;

import com.car.core.api.Const;
import com.car.core.mvp.presenter.BasePresenter;
import com.car.core.utils.util.UrlParam;

import java.util.WeakHashMap;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabhome.home.mvp
 * @time 2019/11/10 17:55
 * @description
 */
public class HomePersenterImpl extends BasePresenter<HomeContract.IHomeView, HomeModel>
        implements HomeContract.IHomePersenter {

    @Override
    protected HomeModel attachModel() {
        return new HomeModel();
    }


    @Override
    public void onRequestIndex(WeakHashMap map) {
        String index = UrlParam.getParam(Const.API_USER_INDEX, "index");
        getModel().request(index, map, getLifecycleOwner(), (Observer<String>) s -> {
            getView().onResultIndex(s);
        });
    }

    @Override
    public void onRequestICityCode(WeakHashMap map) {
        String index = UrlParam.getParam(Const.API_USER_STORE, "getCityCode");
        getModel().request(index, map, getLifecycleOwner(), (Observer<String>) s -> {
            getView().onResultCityCode(s);
        });
    }
}
