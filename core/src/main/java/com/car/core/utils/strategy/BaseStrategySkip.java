package com.car.core.utils.strategy;

import com.car.core.mvp.view.BaseMvpDelegate;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.core.utils.strategy
 * @time 2019/11/7 22:44
 * @description 策略模式，在 item 中实现跳转，避免使用 switch 或者 if else
 */
public interface BaseStrategySkip {
    /**
     * 跳转方法
     *
     * @param skipDelegate 当前的 delegate
     */
    void onSkip(BaseMvpDelegate skipDelegate);
}
