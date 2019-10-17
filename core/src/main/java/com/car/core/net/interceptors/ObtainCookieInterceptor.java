package com.car.core.net.interceptors;

import android.util.Log;

import com.car.core.net.callback.IFailure;
import com.car.core.utils.storage.CarPreference;

import java.io.IOException;

import javax.security.auth.login.LoginException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.core.net.interceptors
 * @time 2019/10/17 20:46
 * @description
 */
public class ObtainCookieInterceptor extends BaseInterceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        if (InterceptorsManage.IS_OBTAIN_COOKIE_INTERCEPTOR) {
            Request request = chain.request();
            Response response = chain.proceed(request);
            String cookie = response.header("set-cookie");
            Log.e("---------", "获取 intercept: "+cookie);
            // 取得 sessid
            if (cookie != null) {
                CarPreference.putCookie(cookie.substring(0, cookie.indexOf(";")));
            }
            return response;
        }
        return chain.proceed(chain.request());
    }
}
