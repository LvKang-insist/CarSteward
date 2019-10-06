package com.car.core.mvp.factory;

import com.car.core.mvp.model.BaseModel;
import com.car.core.mvp.presenter.BasePresenter;
import com.car.core.mvp.view.IBaseView;

/**
 * @author 345 QQ:1831712732
 * @name MvpFrame
 * @class name：com.latte.core.mvp.factory
 * @time 2019/8/30 17:12
 * @description P层的工厂，通过注解 CreatePresenter 来生成 P层的实例
 */
public class PresenterFactoryImpl<V extends IBaseView, P extends BasePresenter<V, BaseModel>>{

    public static <V extends IBaseView, P extends BasePresenter<V, BaseModel>> P createPresenterFactory(Class<?> viewClass) {
        CreatePresenter annotation = viewClass.getAnnotation(CreatePresenter.class);
        Class<P> pClass = null;
        if (annotation != null) {
            pClass = (Class<P>) annotation.value();
        }
        try {
            return pClass != null ? pClass.newInstance() : null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

   /* private Class<P> mPresenterClass;

    private PresenterFactoryImpl(Class<P> presenterClass) {
        mPresenterClass = presenterClass;
    }

    public static  <V extends IBaseView, P extends BasePresenter<V, BaseModel>> P createPresenterFactory(Class<?> viewClass) {
        CreatePresenter annotation = viewClass.getAnnotation(CreatePresenter.class);
        Class<P> pClass = null;
        if (annotation != null) {
            pClass = (Class<P>) annotation.value();
        }
        return pClass != null ? new PresenterFactoryImpl<>(pClass).createPresenter() : null;
    }


    private P createPresenter() {
        try {
            return mPresenterClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Presenter 创建失败，请确定是否声明了 @CreatePresenter");
        }

    }*/


}
