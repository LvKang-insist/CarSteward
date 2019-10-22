package com.car.core.net;

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
    public ResponseBody responseBody;


    public CustomResponse(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    public ResponseBody getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
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
