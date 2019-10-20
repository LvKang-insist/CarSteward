package com.car.tabmine.LiveDataRetrofit;

import androidx.lifecycle.LiveData;

import retrofit2.http.GET;

/**
 * Created by Petterp
 * on 2019-10-20
 * Function:
 */
public interface TestService {
    @GET("https://www.baidu.com/")
    LiveData<String> getBaidu();
}
