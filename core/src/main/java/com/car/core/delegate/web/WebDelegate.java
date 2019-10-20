package com.car.core.delegate.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.annotation.Nullable;

import com.car.core.delegate.web.route.RouteKeys;
import com.car.core.latte.ConfigKeys;
import com.car.core.latte.Latte;
import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl;
import com.car.core.mvp.view.BaseMvpFragment;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.core.delegate.web
 * @time 2019/10/20 15:07
 * @description
 */
@CreatePresenter(DefaultPresenterImpl.class)
public abstract class WebDelegate extends BaseMvpFragment<DefaultPresenterImpl> implements IWebViewInitializer {

    private WebView mWebView = null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    private String mUrl = null;
    private BaseMvpFragment mTopDelegate = null;

    private boolean mIsWebViewAbailable = false;

    /**
     * 初始化方法
     *
     * @return
     */
    public abstract IWebViewInitializer setInitializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            final Bundle bundle = getArguments();
            //拿到需要加载的网页
            mUrl = bundle.getString(RouteKeys.URL.name());
        }
        initWebView();
    }

    @SuppressLint("AddJavascriptInterface")
    protected void initWebView() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            final IWebViewInitializer initializer = setInitializer();
            if (initializer != null) {
                //软引用，避免内存泄露，创建WebView 的对象
                final WeakReference<WebView> webViewWeakReference =
                        new WeakReference<>(new WebView(getContext()), WEB_VIEW_QUEUE);
                mWebView = webViewWeakReference.get();
                //初始化WebView
                mWebView = initializer.initWebView(mWebView);
                //处理各种通知，请求事件
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());

                final String name = Latte.getValue(ConfigKeys.JAVASCRIPT_INTERFACE);
                mWebView.addJavascriptInterface(LatteWebInterface.crate(this), name);
                mIsWebViewAbailable = true;

            } else {
                throw new NullPointerException("Initializer is null");
            }
        }
    }

    /**
     * 设置 delegate
     */
    public void setTopDelegate(BaseMvpFragment delegate) {
        mTopDelegate = delegate;
    }

    /**
     * 获取 delegate
     */
    public BaseMvpFragment getTopDelegate() {
        if (mTopDelegate == null) {
            mTopDelegate = this;
        }
        return mTopDelegate;
    }

    /**
     * 获取 webView
     */
    public WebView getWebView() {
        if (mWebView == null) {
            throw new NullPointerException("WebView IS NULL");
        }
        return mIsWebViewAbailable ? mWebView : null;
    }

    public String getUrl() {
        return mUrl;
    }

    public boolean isWebBack() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return false;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAbailable = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }
}
