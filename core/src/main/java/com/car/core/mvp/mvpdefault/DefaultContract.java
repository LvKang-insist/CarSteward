package com.car.core.mvp.mvpdefault;

import com.car.core.mvp.presenter.IBasePresenter;
import com.car.core.mvp.view.BaseMvpFragment;
import com.car.core.mvp.view.IBaseView;

import java.util.WeakHashMap;

/**
 * @author 345 QQ:1831712732
 * @name MvpFrame
 * @class name：com.latte.core.mvp.mvpdefault
 * @time 2019/9/10 21:53
 * @description 默认的mvp 实现接口
 */
public class DefaultContract {
    public interface IDefaultView extends IBaseView {
        /**
         * 网络请求的结果回调
         * @param result 结果
         */
        void onResult(String result);
    }
    public interface IDefaultPresenter extends IBasePresenter<IDefaultView> {
        /**
         * 默认的网络请求接口
         * @param url 地址
         * @param param 参数
         */
        void request(String url, WeakHashMap param);
    }
}
