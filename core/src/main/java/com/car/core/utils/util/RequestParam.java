package com.car.core.utils.util;

import com.car.core.utils.storage.CarPreference;

import java.util.WeakHashMap;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.core.utils.util
 * @time 2019/10/24 21:52
 * @description
 */
public class RequestParam {

    public WeakHashMap param;

    private RequestParam(WeakHashMap param) {
        param = new WeakHashMap();
    }

    public static ParamBuilder builder() {
        return new ParamBuilder();
    }


    public static class ParamBuilder {
        private WeakHashMap param;

        public ParamBuilder() {
            param = new WeakHashMap();
        }

        public ParamBuilder addTokenId() {
            param.put("tokenId", CarPreference.getTokenId());
            return this;
        }

        public ParamBuilder addParam(String key, Object value) {
            param.put(key, value);
            return this;
        }

        public WeakHashMap<String, Object> build() {
            return new RequestParam(param).param;
        }

    }
}
