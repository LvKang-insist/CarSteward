package com.car.core.utils.strategy;

import com.car.core.mvp.view.BaseMvpDelegate;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.core.utils.strategy
 * @time 2019/11/7 23:00
 * @description 跳转环境
 */
public class BaseStrategySkipContext {

    private static BaseStrategySkipContext skipContext = null;
    private BaseMvpDelegate delegate;

    /**
     * @param delegate 当前显示的界面
     */
    private BaseStrategySkipContext(BaseMvpDelegate delegate) {
        this.delegate = delegate;
    }

    public static <T extends BaseMvpDelegate> BaseStrategySkipContext newInstance(T delegate) {
        if (skipContext == null) {
            skipContext = new BaseStrategySkipContext(delegate);
        }
        return skipContext;
    }

    /**
     * 策略接口
     */
    private BaseStrategySkip strategySkip;

    /**
     * 具体的业务
     *
     * @param strategySkip
     */
    public BaseStrategySkipContext setStrategySkip(BaseStrategySkip strategySkip) {
        this.strategySkip = strategySkip;
        return this;
    }

    /**
     * 执行业务
     */
    public void skip() {
        strategySkip.onSkip(delegate);
    }
}
