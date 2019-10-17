package com.car.core.net.interceptors;

import android.util.Log;

import com.car.core.utils.storage.CarPreference;

import java.io.IOException;

import butterknife.internal.Utils;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.core.net.interceptors
 * @time 2019/10/17 20:50
 * @description
 */
public class UpCookieInterceptor extends BaseInterceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        if (InterceptorsManage.IS_UP_COOKIE_INTERCEPTOR) {
            Request.Builder builder = chain.request().newBuilder();
            String cookie = CarPreference.getCookie();
            Log.e("---------", "上传 intercept: "+cookie);
            builder.addHeader("cookie", cookie);
            return chain.proceed(builder.build());
        }
        return chain.proceed(chain.request());
    }
}
