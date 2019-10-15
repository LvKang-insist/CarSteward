package com.car.core.mvp.view;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.car.core.R;
import com.car.core.delegate.base.PermissionCheckerDelegate;
import com.car.core.latte.Latte;
import com.car.core.mvp.factory.PresenterFactoryImpl;
import com.car.core.mvp.presenter.IBasePresenter;
import com.car.core.ui.dialog.ToastDialog;
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

public abstract class BaseMvpFragment<P extends IBasePresenter> extends PermissionCheckerDelegate
        implements IBaseView, BaseMvpActivity.OnBackPressListener {

    private P mPresenter;
    public Gson gson = new Gson();
    private boolean mDialogIsShow = false;
    private ToastDialog mLoadingDilaog;

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

    public <T extends BaseMvpFragment> T getParentDelegate() {
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
        Latte.getBaseMvpActivity().setOnBackPressListener(this);
        initImmersion();
//        绑定 ButterKnife
        unbinder = ButterKnife.bind(this, rootView);
        bindView(rootView);
//        将 Lifecycle 对象和LifecycleObserver 对象进行绑定
        getLifecycle().addObserver(mPresenter);
        //订阅
        if (isEventBus()) {
            EventBus.getDefault().register(this);
        }
        return rootView;
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
        stopLoading();
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
                .transparentStatusBar()
                .titleBarMarginTop(getToolView())
                // 默认状态栏字体颜色为黑色
                .statusBarDarkFont(true);
        if (getToolView() instanceof Toolbar) {
            setBack(((Toolbar) getToolView()));
        }
        return mImmersionBar;
    }

    /**
     * 显示加载对话框
     *
     * @param msg 若为 null ，显示正在加载，否则显示 msg
     */
    public void showLoading(String msg) {
        createLoadingDialog(msg);
        mLoadingDilaog
                .setType(ToastDialog.Type.LOADING)
                .show(getChildFragmentManager(), "register");
    }

    /**
     * 初始化 loading
     *
     * @param msg 消息
     */
    private void createLoadingDialog(String msg) {
        if (msg == null) {
            msg = "正在加载...";
        }
        mLoadingDilaog = ToastDialog.ToastBuilder()
                .setContentView(R.layout.dialog_toast)
                .setGravity(Gravity.CENTER)
                .build()
                .setMessage(msg);
        mDialogIsShow = true;
    }

    /**
     * 关闭 Loading
     */
    public void stopLoading() {
        if (mLoadingDilaog != null && mDialogIsShow) {
            mLoadingDilaog.dismiss();
            mLoadingDilaog = null;
        }
    }

    protected void setBack(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(v -> {
            if (!setBackPress()) {
                getSupportDelegate().pop();
            }
        });
    }


    private View getToolView() {
        int id = getToolbar();
        if (id != 0) {
            return rootView.findViewById(id);
        }
        return null;
    }

    /**
     * 需要被重写
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
     * 如果需要拦截返回事件，则重写该方法 return true 即可
     */
    @Override
    public boolean setBackPress() {
        return false;
    }

    /**
     * 有父fragment的基本跳转
     */
    public void parentfragmentAnimStart(BaseMvpFragment fragment) {
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
     * 无父fragment的基本跳转
     */
    public void fragmentAnimStart(BaseMvpFragment fragment) {
        getSupportDelegate()
                .extraTransaction()
                .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out,
                        R.anim.slide_left_in, R.anim.slide_left_out)
                .start(fragment);
    }

    /**
     * 有父fragment的退栈
     */
    public void fragmentAnimBack() {
        getSupportDelegate().pop();
    }

}
