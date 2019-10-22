package com.car.core.net.rx;


import androidx.lifecycle.LiveData;

import com.car.core.net.CustomResponse;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Copyright (C)
 *
 * @file: RestService
 * @author: 345
 * @Time: 2019/4/17 17:40
 * @description: ${DESCRIPTION}
 */
public interface RxRestService {
    /**
     * get请求
     *
     * @param url    地址
     * @param params 参数
     * @return 返回一个Call
     */
    @GET
    LiveData<String> get(@Url String url, @QueryMap Map<String, Object> params);

    @GET
    LiveData<String> test(@Url String url, @QueryMap Map<String, Object> params);

    @GET
    LiveData<CustomResponse> getCookie(@Url String url, @QueryMap Map<String, Object> params);

    @GET
    LiveData<String> addCookie(@Header("cookie") String cookie, @Url String url, @QueryMap Map<String, Object> params);

    @GET
    LiveData<Byte> getImage(@Url String url, @QueryMap Map<String, Object> params);

    @FormUrlEncoded
    @POST
    LiveData<String> post(@Url String url, @FieldMap Map<String, Object> params);

    /**
     * 传入原始 数据就不能添加 @FormUrlEncoded 了
     */
    @POST
    LiveData<String> postRaw(@Url String url, @Body RequestBody body);

    @FormUrlEncoded
    @PUT
    LiveData<String> put(@Url String url, @FieldMap Map<String, Object> params);

    @PUT
    LiveData<String> putRaw(@Url String url, @Body RequestBody body);

    @DELETE
    LiveData<String> delete(@Url String url, @QueryMap Map<String, Object> params);

    /**
     * download 默认是吧文件下载到内存中，最后统一写入文件里，这种方式存在一个问题：
     * 当文件过大是 会导致文件溢出，所以这里加入@Streaming ，代表一边下载一边写入到文件中
     * 这样就避免了 一次性在内存中写入过大的文件造成的问题
     */
    @Streaming
    @GET
    LiveData<ResponseBody> download(@Url String url, @QueryMap Map<String, Object> params);

    @Streaming
    @GET
    LiveData<ResponseBody> download(@Url String url);


    /**
     * @param url  地址
     * @param file 参数
     * @return 返回Call
     */
    @Multipart
    @POST
    LiveData<String> upload(@Url String url, @Part MultipartBody.Part file);

}
