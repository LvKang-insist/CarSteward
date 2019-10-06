package com.car.core.mvp.factory;


import com.car.core.mvp.presenter.BasePresenter;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author 345 QQ:1831712732
 * @name MvpFrame
 * @class name：com.latte.core.mvp.factory
 * @time 2019/8/30 17:12
 * @description 注解，用来生成P层的实例，所有的 Activity 或 Fragment 都必须使用该注解，
 *             否则将会 抛出 RuntimeException Presenter 创建失败，请确定是否声明了@CreatePresenter
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenter {
    Class<? extends BasePresenter> value();
}
