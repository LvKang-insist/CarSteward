package com.car.core.api;

import static com.car.core.api.BaseUrl.BASE_URL;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.core.api
 * @time 2019/10/14 22:10
 * @description
 */
public interface Const {
    String API_BASE_USER = BASE_URL + "index.php?m=App&c=APIUsers";
    String API_BASE_URL_PUBLIC = BASE_URL + "index.php?m=App&c=APIPublic";
    /**
     * 自营商品接口
     */
    String API_MALL = BASE_URL + "index.php?m=App&c=APIMall";
}
