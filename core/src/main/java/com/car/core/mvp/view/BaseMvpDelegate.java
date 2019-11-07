package com.car.core.mvp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.car.core.R;
import com.car.core.delegate.base.PermissionCheckerDelegate;
import com.car.core.mvp.factory.PresenterFactoryImpl;
import com.car.core.mvp.presenter.IBasePresenter;
import com.car.core.utils.dimen.SetToolBar;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author 345 QQ:1831712732
 * @name MvpFrame
 * @class name：com.latte.core.mvp.view
 * @time 2019/8/30 17:12
 * @description 抽象类， Fragment 必须继承此类，该类会调用 P 层的生命周期方法
 */

public abstract class BaseMvpDelegate<P extends IBasePresenter> extends PermissionCheckerDelegate
        implements IBaseView {

    private P mPresenter;
    public Gson gson = new Gson();

    /**
     * 设置布局
     *
     * @return 可以是一个View ，也可以是一个Layout的Id，代表一个视图
     */
    public abstract Object setLayout();

    /**
     * 必须重写此方法，他在 onCreateView 执行后调用。
     *
     * @view 当前fragmetn 的视图
     */
    public abstract void bindView(View view);

    private Unbinder unbinder = null;

    private View rootView;

    /**
     * 状态栏沉浸
     */
    private ImmersionBar mImmersionBar;

    public <T extends BaseMvpDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            if (setLayout() instanceof Integer) {
                rootView = inflater.inflate((Integer) setLayout(), container, false);
            } else if (setLayout() instanceof View) {
                rootView = (View) setLayout();
            } else {
                throw new NullPointerException("setLayout() must be int or View Error!");
            }
        }
        mPresenter = (P) PresenterFactoryImpl.createPresenterFactory(getClass());
        if (mPresenter == null) {
            throw new NullPointerException("Presenter is null ! Do you return null in createPresenter()");
        }
        mPresenter.onMvpAttachView(this, savedInstanceState);
        initView(savedInstanceState);
        return rootView;
    }

    private void initView(@Nullable Bundle savedInstanceState) {
//        绑定 ButterKnife
        unbinder = ButterKnife.bind(this, rootView);
//        将 Lifecycle 对象和LifecycleObserver 对象进行绑定
        getLifecycle().addObserver(mPresenter);
        //订阅
        if (isEventBus()) {
            EventBus.getDefault().register(this);
        }
        initToolbar();
//        setImmersion(R.color.colorPrimaryDark);
        bindView(rootView);
    }

    public P getPresenter() {
        return mPresenter;
    }


    @Override
    public void onSaveInstanceState(@Nullable Bundle outState) {
        assert outState != null;
        super.onSaveInstanceState(outState);
        if (mPresenter != null) {
            mPresenter.onMvpSaveInstanceState(outState);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder = null;
        rootView = null;
        if (isEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    /**
     * 获取 View
     *
     * @return view
     */
    public View getRootView() {
        return rootView;
    }

    /**
     * 获取沉浸式状态栏实例
     *
     * @return 沉浸式状态栏
     */
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
    protected void initToolbar() {
        if (getToolView() instanceof Toolbar) {
            setBack(((Toolbar) getToolView()));
            SetToolBar.setToolBar(getToolView());
        }else if (getToolView() instanceof  ViewGroup){
            SetToolBar.setToolBar(getToolView());
        }

    }


    private void setBack(Toolbar toolbar) {
        toolbar.getChildAt(0).setOnClickListener(v -> getSupportDelegate().pop());
    }


    private View getToolView() {
        int id = getToolbar();
        if (id != 0) {
            return rootView.findViewById(id);
        }
        return null;
    }

    /**
     * 此方法需要重写
     */
    public int getToolbar() {
        return 0;
    }

    /**
     * 是否开启 eventbus
     *
     * @return true 为开启
     */
    public boolean isEventBus() {
        return false;
    }

    /**
     * 有父fragment的基本跳转
     */
    public void parentfragmentAnimStart(BaseMvpDelegate fragment) {
        getParentDelegate()
                .getSupportDelegate()
                .extraTransaction()
                .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out,
                        R.anim.slide_left_in, R.anim.slide_left_out)
                .start(fragment);
    }

    /**
     * 有父fragment的退栈
     */
    public void parentfragmentAnimBack() {
        getParentDelegate().getSupportDelegate().pop();
    }

    /**
     * 没有动画的基本跳转
     *
     * @param fragment
     */
    public void fragmentStart(BaseMvpDelegate fragment) {
        getSupportDelegate().start(fragment);
    }

    public void parentfragmenttart(BaseMvpDelegate fragment) {
        getParentDelegate().getSupportDelegate().start(fragment);
    }


    /**
     * 无父fragment的基本跳转
     */
    public void fragmentAnimStart(BaseMvpDelegate fragment) {
        getSupportDelegate()
                .extraTransaction()
                .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out,
                        R.anim.slide_left_in, R.anim.slide_left_out)
                .start(fragment);
    }


    /**
     * fragment的退栈
     */
    public void fragmentAnimBack() {
        getSupportDelegate().pop();
    }

}
