package com.car.core.utils.bean;

import com.car.core.latte.Latte;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.core.utils.bean
 * @time 2019/11/10 15:51
 * @description
 */
public class DataBean {
    public static <T extends DataBean> T toData(String result, Class<T> tClass) {
        return Latte.getGson().fromJson(result, tClass);
    }
}
