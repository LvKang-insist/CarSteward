package com.car.core.net.lvdata;


import com.car.core.net.lvdata.CustomResponse;
import com.elvishew.xlog.XLog;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
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
public class LiveDataResponseConverterFactory extends Converter.Factory {

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type == String.class) {
            return new StringConverter();
        } else if (type == CustomResponse.class) {
            return new ResponseConverter();
        } else {
            return null;
        }
    }

    class StringConverter implements Converter<ResponseBody, String> {
        @Override
        public String convert(ResponseBody value) throws IOException {
            return value.string();
        }
    }

    class ResponseConverter implements Converter<ResponseBody, CustomResponse> {
        @Override
        public CustomResponse convert(ResponseBody value) throws IOException {
            return new CustomResponse(value.string());
        }
    }


}
