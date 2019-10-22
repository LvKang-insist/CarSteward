package com.car.core.net;

import android.util.Log;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.core.net
 * @time 2019/10/22 20:30
 * @description
 */
public class AddResponse extends Converter.Factory {
    private static final MediaType MEDIA_TYPE = MediaType.parse("text/plain; charset=UTF-8");

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {

        if (type == String.class) {
            Log.e("-----", "requestBodyConverter: 请求");
        }
        return new Converter<Object, RequestBody>() {
            @Override
            public RequestBody convert(Object value) throws IOException {
                return RequestBody.create(MEDIA_TYPE, String.valueOf(value));
            }
        };
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        Log.e("------", "哈哈哈responseBodyConverter: " + type);
        if (type == String.class) {
            return new Converter<ResponseBody, String>() {
                @Override
                public String convert(ResponseBody value) throws IOException {
                    String str = value.string();
                    Log.e("-----", "响应结果: " + str);
                    return str;
                }
            };
        }
        return new Converter<ResponseBody, CustomResponse>() {
            @Override
            public CustomResponse convert(ResponseBody value) throws IOException {
                return new CustomResponse(value);
            }
        };
    }


}
