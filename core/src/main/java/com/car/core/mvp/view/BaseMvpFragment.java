package com.car.core.mvp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.car.core.delegate.base.PermissionCheckerDelegate;
import com.car.core.latte.Latte;
import com.car.core.mvp.factory.PresenterFactoryImpl;
import com.car.core.mvp.presenter.IBasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author 345 QQ:1831712732
 * @name MvpFrame
 * @class name：com.latte.core.mvp.view
 * @time 2019/8/30 17:12
 * @description 抽象类， Fragment 必须继承此类，该类会调用 P 层的生命周期方法
 */

public abstract class BaseMvpFragment<P extends IBasePresenter> extends PermissionCheckerDelegate
        implements IBaseView,  BaseMvpActivity.OnBackPressListener{

    private P mPresenter;

    public abstract Object setLayout();

    public abstract void BindView(View view);

    private Unbinder unbinder = null;

    private View rootView;

    public <T extends BaseMvpFragment> T getParentDelegate(){
        return (T)getParentFragment();
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
        Latte.getBaseMvpActivity().setOnBackPressListener(this);

//        绑定 ButterKnife
        unbinder = ButterKnife.bind(this,rootView);
        BindView(rootView);
//        将 Lifecycle 对象和LifecycleObserver 对象进行绑定
        getLifecycle().addObserver(mPresenter);
        return rootView;
    }

    public P getPresenter(){
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
        if (unbinder != null){
            unbinder.unbind();
            unbinder = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder = null;
        rootView = null;
    }


    public View getRootView(){
        return rootView;
    }

    /**
     *  如果需要拦截返回事件，则重写该方法 return true 即可
     */
    @Override
    public boolean setBackPress() {
        return false;
    }

    /**
     * fragment基本跳转
     * A->B
     */
    public void fragmentStart(@IdRes int id) {

    }
    /**
     * fragment 携带数据跳转-Bundle
     * A->B
     */
    public void fragmentStart(@IdRes int id, @Nullable Bundle bundle) {

    }

    /**
     * 退栈方法
     */
    public void fragmentUp() {

    }

}
