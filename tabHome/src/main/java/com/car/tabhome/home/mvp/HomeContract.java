package com.car.tabhome.home.mvp;

import com.car.core.mvp.presenter.IBasePresenter;
import com.car.core.mvp.view.IBaseView;
import com.car.core.utils.bean.DataBean;
import com.car.core.utils.bean.GetStylesBean;

import java.util.WeakHashMap;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabhome.home.mvp
 * @time 2019/11/10 17:55
 * @description
 */
public interface HomeContract {

    interface IHomeView extends IBaseView {
        /**
         * index
         *
         * @param result
         */
        void onResultIndex(String result);

        /**
         * citycode
         *
         * @param result
         */
        void onResultCityCode(String result);

        /**
         * styles
         *
         * @param bean
         */
        void onResultStyles(GetStylesBean bean);
    }

    interface IHomePersenter extends IBasePresenter<IHomeView> {

        /**
         * index
         *
         * @param map
         */
        void onRequestIndex(WeakHashMap map);

        /**
         * 获取城市 code
         *
         * @param map
         */
        void onRequestICityCode(WeakHashMap map);

        /**
         * index
         */
         void onResultIStyles();

    }

}
