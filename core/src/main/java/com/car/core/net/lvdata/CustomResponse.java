package com.car.core.net.lvdata;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.core.net
 * @time 2019/10/22 22:30
 * @description
 */
public class CustomResponse {
    public Response response;
    public String  result;

    public CustomResponse(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public CustomResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
