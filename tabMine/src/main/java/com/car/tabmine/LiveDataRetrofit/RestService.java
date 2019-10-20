package com.car.tabmine.LiveDataRetrofit;


import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 通用Retrofit接口
 */
public interface RestService {


    @GET
    LiveData<String> get(@Url String url, @QueryMap Map<String, Object> params);

    @FormUrlEncoded
    @POST
    LiveData<String> post(@Url String url, @FieldMap Map<String, Object> params);

    /**
     * 原始数据
     *
     * @param url
     * @param body
     * @return
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
     * 这里为了防止内存溢出，默认情况是下载好存储，当文件过大时，不建议采取。
     * 这里改用边下载边存储
     *
     * @param url
     * @param params
     * @return
     */
    @Streaming
    @GET
    LiveData<ResponseBody> download(@Url String url, @QueryMap Map<String, Object> params);

    /**
     * 多文件上传
     *
     * @param url
     * @param partList
     * @return
     */
    @Multipart
    @POST
    LiveData<String> upload(@Url String url, @Part List<MultipartBody.Part> partList);

    @Multipart
    @POST()
    LiveData<String> uploadImg(@Url String url, @Part MultipartBody.Part file);

}
