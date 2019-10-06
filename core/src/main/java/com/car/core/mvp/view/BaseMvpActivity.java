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

/**
 * @author 345 QQ:1831712732
 * @name MvpFrame
 * @class name：com.latte.core.mvp.view
 * @time 2019/8/30 17:12
 * @description 抽象类， Activity 必须继承此类
 */
public abstract class BaseMvpActivity<P extends IBasePresenter> extends BaseActivity implements IBaseView {

    public P mPresenter;

    public abstract BaseDelegate setRootDelegate();

    public abstract void BindView();

    public OnBackPressListener onBackPress;

    public interface OnBackPressListener {
        boolean setBackPress();
    }

    public void setOnBackPressListener(OnBackPressListener onBackPress) {
        this.onBackPress = onBackPress;
    }

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
        mPresenter.onMvpAttachView(this, savedInstanceState);
        Latte.init(this)
                .withBaseMvpActivity(this)
                .configure();
        BindView();
//        将 Lifecycle 对象和LifecycleObserver 对象进行绑定
        getLifecycle().addObserver(mPresenter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mPresenter != null) {
            mPresenter.onMvpSaveInstanceState(outState);
        }
    }



    /*  @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (onBackPress != null && onBackPress.setBackPress()) {
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }*/
}
