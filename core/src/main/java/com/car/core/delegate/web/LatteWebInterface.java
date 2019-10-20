package com.car.core.delegate.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.car.core.delegate.web.event.Event;
import com.car.core.delegate.web.event.EventManager;

/**
 * Copyright (C)
 *
 * @file: LatteWebInterface
 * @author: 345
 * @Time: 2019/5/4 15:45
 * @description: ${DESCRIPTION}
 */
public class LatteWebInterface {
    private final WebDelegate delegate;


    public LatteWebInterface(WebDelegate delegate) {
        this.delegate = delegate;
    }

    static LatteWebInterface crate(WebDelegate delegate) {
        return new LatteWebInterface(delegate);
    }

    @JavascriptInterface
    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        if (event!= null){
            event.setAction(action);
            event.setDelegate(delegate);
            event.setContext(delegate.getContext());
            event.setUrl(delegate.getUrl());
            return event.execute(params);
        }
        return null;
    }
}
