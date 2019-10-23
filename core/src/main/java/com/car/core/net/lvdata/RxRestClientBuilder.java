package com.car.core.net.lvdata;

import com.car.core.net.RestCreator;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Copyright (C)
 *
 * @file: RestClientBuilder
 * @author: 345
 * @Time: 2019/4/17 19:48
 * @description: ${DESCRIPTION}
 */
public class RxRestClientBuilder {
    private String mUrl = null;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private RequestBody mBody = null;
    private File mFile = null;
    private String mCookie = null;

    RxRestClientBuilder() {
    }

    public final RxRestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String, Object> params) {
        if (params != null) {
            PARAMS.putAll(params);
        }
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RxRestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RxRestClientBuilder cookie(String cookie) {
        this.mCookie = cookie;
        return this;
    }

    public final RxRestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RxRestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=urf-8"), raw);
        return this;
    }

    public final RxRestClient build() {
        return new RxRestClient(mUrl, PARAMS, mBody, mFile, mCookie);
    }

}
