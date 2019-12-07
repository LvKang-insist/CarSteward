package com.car.ui.delegate.shoplist.mvp;

import com.car.core.mvp.presenter.IBasePresenter;
import com.car.core.mvp.view.IBaseView;

import java.util.WeakHashMap;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.ui.delegate.shoplist.mvp
 * @time 2019/11/27 21:12
 * @description
 */
public interface ShopListContract {

    interface IShopListView extends IBaseView {
        /**
         * 店铺列表
         *
         * @param carService
         */
        void resultCarService(String carService);

        /**
         * 门店列表
         *
         * @param shopList
         */
        void resultShopList(String shopList);
    }

    interface IShopListPersenterImpl extends IBasePresenter<IShopListView> {
        /**
         * 获取店铺列表
         *
         * @param param
         */
        void requestCarService(WeakHashMap<String, Object> param);

        /**
         * 获取门店列表
         *
         * @param param
         */
        void requestShopList(WeakHashMap<String, Object> param);
    }
}
