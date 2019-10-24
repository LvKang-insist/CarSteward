package com.car.tabmine.mvp;

import com.car.core.mvp.presenter.IBasePresenter;
import com.car.core.mvp.view.IBaseView;

import java.util.List;
import java.util.WeakHashMap;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.mvp
 * @time 2019/10/10 22:39
 * @description
 */
public class MineContract {

    public interface IMineView extends IBaseView {

        void onResult(String result);

        void onUserCenter(UserCenterBean bean);

        /**
         * gradview 数据
         *
         * @param list 数据
         */
        void setGvOne(List<TextStringBean> list);

        void setGvTwo(List<TextImageBean> list);

        void setGvThree(List<TextImageBean> list);
    }

    public interface IMinePresenter extends IBasePresenter<IMineView> {
        void request(String url, WeakHashMap param);

        void requestUserCenter(String url, WeakHashMap param);
    }
}
