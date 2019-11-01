package com.car.core.mvp.view;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.car.core.R;
import com.car.core.activity.BaseActivity;
import com.car.core.delegate.base.BaseDelegate;
import com.car.core.latte.Latte;
import com.car.core.mvp.factory.PresenterFactoryImpl;
import com.car.core.mvp.presenter.IBasePresenter;
import com.gyf.immersionbar.ImmersionBar;

/**
 * @author 345 QQ:1831712732
 * @name MvpFrame
 * @class name：com.latte.core.mvp.view
 * @time 2019/8/30 17:12
 * @description 抽象类， Activity 必须继承此类
 */
public abstract class BaseMvpActivity<P extends IBasePresenter> extends BaseActivity implements IBaseView {

    /**
     * 状态栏沉浸
     */
    private ImmersionBar mImmersionBar;


    public P mPresenter;

    public abstract BaseDelegate setRootDelegate();

    public abstract void bindView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载根fragment
        FrameLayout container = new FrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            DELEGATE.loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
        mPresenter = (P) PresenterFactoryImpl.createPresenterFactory(getClass());
        if (mPresenter == null) {
            throw new NullPointerException("Presenter is null ! Do you return null in createPresenter()?");
        }
        Latte.init(Latte.getAppContext())
                .withBaseMvpActivity(this);
        mPresenter.onMvpAttachView(this, savedInstanceState);
        bindView();
//        将 Lifecycle 对象和LifecycleObserver 对象进行绑定
        getLifecycle().addObserver(mPresenter);
        initImmersion();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mPresenter != null) {
            mPresenter.onMvpSaveInstanceState(outState);
        }
    }

    public ImmersionBar getStatusBarConfig() {
        return mImmersionBar;
    }

    /**
     * 是否使用沉浸式状态栏
     */
    public boolean isStatusBarEnabled() {
        return true;
    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersion() {
        // 初始化沉浸式状态栏
        if (isStatusBarEnabled()) {
            statusBarConfig().init();
        }
    }

    /**
     * 初始化沉浸式状态栏
     */
    protected ImmersionBar statusBarConfig() {
        // 在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this)
                .statusBarColor(R.color.transparent)
                // 默认状态栏字体颜色为黑色
                .statusBarDarkFont(true);
        return mImmersionBar;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /*  @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (onBackPress != null && onBackPress.setBackPress()) {
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }*/
}
