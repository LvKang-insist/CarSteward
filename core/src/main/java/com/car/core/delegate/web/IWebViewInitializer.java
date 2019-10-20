package com.car.core.delegate.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Copyright (C)
 *
 * @file: IWebViewInitializer
 * @author: 345
 * @Time: 2019/5/4 15:32
 * @description: ${DESCRIPTION}
 */
public interface IWebViewInitializer {

    /**
     * 初始化
     *
     * @param webView 需要初始化的 web
     * @return webview
     */
    WebView initWebView(WebView webView);

    /**
     * 针对浏览器本身行为的一个控制
     *
     * @return
     */
    WebViewClient initWebViewClient();

    /**
     * 针对页面的 一个控制
     *
     * @return
     */
    WebChromeClient initWebChromeClient();
}
