package com.car.core.net.interceptors;

import android.util.Log;

import com.elvishew.xlog.XLog;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.Response;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.core.net.interceptors
 * @time 2019/11/3 18:05
 * @description
 */
public class TestInterceptor extends BaseInterceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        XLog.e("拦截器："+chain.request().url());
        LinkedHashMap<String, String> urlParameters = getUrlParameters(chain);
        for (Map.Entry<String, String> stringEntry: urlParameters.entrySet()){
            Log.e("345",stringEntry.getKey()+"-----"+stringEntry.getValue());
        }
        return chain.proceed(chain.request());
    }
}
