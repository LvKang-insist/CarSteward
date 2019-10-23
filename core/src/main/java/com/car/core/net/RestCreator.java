package com.car.core.net;


import com.car.core.api.BaseUrl;
import com.car.core.net.interceptors.BaseInterceptor;
import com.car.core.net.lvdata.RxRestService;

import java.util.WeakHashMap;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Copyright (C)
 *
 * @file: RestCreator
 * @author: 345
 * @Time: 2019/4/17 19:19
 * @description: Creator：创造者
 */
public class RestCreator {

    /**
     * 参数容器
     */
    private static final class ParamsHolder {
        static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    /**
     * 构建 全局Retrofit 客户端
     */
    private static final class RetrofitHolder {
        private static String BASE_URL = BaseUrl.BASE_URL;
        private static Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                //设置网络请求的 url地址
                .baseUrl(BASE_URL)
                .client(OkhttpHolder.OKHTTP_CLIENT)
                //依赖中引入的转换器
                .addConverterFactory(new LiveDataResponseConverterFactory())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build();

    }

    private static final class OkhttpHolder<T extends BaseInterceptor> {
        private static final OkHttpClient OKHTTP_CLIENT = new OkHttpClient().newBuilder()
                .build();
    }

    /**
     * Service 接口
     */
    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    /**
     * @return 返回网络接口请求的实例
     */
    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    /**
     * RxService 接口
     */
    private static final class RxRestServiceHolder {
        //创建 网络请求接口 的实例
        private static final RxRestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);
    }

    public static RxRestService getRxRestService() {
        return RxRestServiceHolder.REST_SERVICE;
    }
}
