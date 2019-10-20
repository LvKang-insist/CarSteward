package com.car.tabmine.LiveDataRetrofit;



import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 获取RestService对象
 */
public class RestCreator {

    private final Retrofit RETROFIT_CLIENT;
    private RestService service;

    private RestCreator() {
        final int TIME_OUT = 60;
        final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        //建造者模式
        final OkHttpClient OK_HTTP_CLIENT = BUILDER
                //传入时间，以秒为单位
                .connectTimeout(NetConstant.API_CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(NetConstant.API_READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(NetConstant.API_WRITE_TIME_OUT, TimeUnit.SECONDS)
                .build();
        String BASE_URL = "http://39.100.95.172:18020";
        RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build();
        service = RETROFIT_CLIENT.create(RestService.class);
    }

    private static class Client {
        private static RestCreator restCreator = new RestCreator();
    }

    public static RestCreator instance() {
        return Client.restCreator;
    }

    public Retrofit getRetrofitClient() {
        return RETROFIT_CLIENT;
    }

    public RestService getService() {
        return service;
    }

    private static final class ParamsHolder {
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }


    /**
     * OkHttp惰性初始化
     */
    private static final class OkHttpHolder {
        //优化效率，避免多次创建
//        private static final ArrayList<Interceptor> INTERCEPTORS = Latte.getConfiguration(ConfigKeys.INTERCEPTOR);
//
//        //通过循环的方式将拦截器传入OkHttp
//        private static OkHttpClient.Builder addInterceptor() {
//            if (INTERCEPTORS != null) {
//                for (Interceptor interceptor : INTERCEPTORS) {
//                    BUILDER.addInterceptor(interceptor);
//                }
//            }
//            return BUILDER;
//        }


    }

}
