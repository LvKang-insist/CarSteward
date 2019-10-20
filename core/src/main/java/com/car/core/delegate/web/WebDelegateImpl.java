package com.car.core.delegate.web;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;

import com.car.core.delegate.web.chromeclient.WebChromeClientImpl;
import com.car.core.delegate.web.client.WebViewClientImpl;
import com.car.core.delegate.web.route.RouteKeys;
import com.car.core.delegate.web.route.Router;
import com.hjq.toast.ToastUtils;

import okhttp3.Route;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.core.delegate.web
 * @time 2019/10/20 15:29
 * @description WebDelegate 实现类
 */
public class WebDelegateImpl extends WebDelegate {

    private IPageLoadListener mIpageLoadListener = null;
    private String mData = null;

    public static WebDelegateImpl create(String url) {
        final Bundle bundle = new Bundle();
        bundle.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl webDelegate = new WebDelegateImpl();
        webDelegate.setArguments(bundle);
        return webDelegate;
    }


    @Override
    public void bindView(View view) {
        if (getUrl() != null) {
            //用原生的 方式模拟web跳转并进行页面加载
            Router.getInstance().loadPage(this, getUrl());
        } else {
            if (mData != null) {
                Router.getInstance().loadData(this, mData);
            }
        }
    }

    public void setPageLoadListener(IPageLoadListener loadListener) {
        this.mIpageLoadListener = loadListener;
    }

    /**
     * @return 返回IWebViewInitializer 接口的实例
     */
    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    public void setData(String data) {
        this.mData = data;
    }

    /**
     * 初始化WebView
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public WebView initWebView(WebView webView) {
        //进行初始化
        return new WebViewInitializer().createWebView(webView);
    }

    /**
     * 处理webView 事件
     */
    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        //传入 IPageLoadListener 接口的实例
        client.setPageLoadListener(mIpageLoadListener);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }


}
