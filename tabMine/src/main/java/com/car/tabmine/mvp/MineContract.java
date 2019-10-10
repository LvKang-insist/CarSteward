package com.car.tabmine.mvp;

import com.car.core.mvp.mvpdefault.DefaultContract;
import com.car.core.mvp.presenter.IBasePresenter;
import com.car.core.mvp.view.BaseMvpFragment;
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

    public interface IMineView extends IBaseView{
        void onResult(boolean flag, String result);
        void setGvThree(List<FunctionItemBean> list);
    }

    public interface IMinePresenter extends IBasePresenter<IMineView> {
        void request(BaseMvpFragment mvpFragment, String url, WeakHashMap param);
    }
}
