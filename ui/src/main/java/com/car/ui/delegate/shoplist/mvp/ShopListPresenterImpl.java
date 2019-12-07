package com.car.ui.delegate.shoplist.mvp;

import androidx.lifecycle.Observer;

import com.car.core.api.Const;
import com.car.core.mvp.presenter.BasePresenter;
import com.car.core.utils.util.UrlParam;

import java.util.WeakHashMap;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.ui.delegate.shoplist.mvp
 * @time 2019/11/27 21:12
 * @description
 */
public class ShopListPresenterImpl extends BasePresenter<ShopListContract.IShopListView, ShopListModel>
        implements ShopListContract.IShopListPersenterImpl {
    String carService = UrlParam.getParam(Const.API_USER_STORE, "getCarServer");
    String shopList = UrlParam.getParam(Const.API_USER_STORE, "getShopList");

    @Override
    protected ShopListModel attachModel() {
        return new ShopListModel();
    }

    @Override
    public void requestCarService(WeakHashMap<String, Object> param) {
        getModel().request(carService, param, getLifecycleOwner(), (Observer<String>) s -> {
            getView().resultCarService(s);
        });
    }

    @Override
    public void requestShopList(WeakHashMap<String, Object> param) {
        getModel().request(shopList, param, getLifecycleOwner(), (Observer<String>) s -> {
            getView().resultShopList(s);
        });
    }
}
