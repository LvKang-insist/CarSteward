package com.car.core.delegate;

import android.widget.Toast;

import com.car.core.mvp.presenter.IBasePresenter;
import com.car.core.mvp.view.BaseMvpDelegate;

/**
 * @author 345 QQ:1831712732
 * @name MvpFrame
 * @class name：com.latte.core.delegate
 * @time 2019/9/25 21:44
 * @description 所有的 tabDelegate 都必须继承自这个类
 */
public abstract class BottomItemDelegate<P extends IBasePresenter> extends BaseMvpDelegate {

    /** 再点一次退出程序时间设置 */
    private static final long WAIT_TIME = 2000L;
    private long time = 0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - time < WAIT_TIME) {
            _mActivity.finish();
        } else {
            time = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public P getPresenter() {
       return (P) super.getPresenter();
    }
}
