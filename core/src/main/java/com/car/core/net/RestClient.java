package com.car.core.net;

import android.content.Context;

import com.car.core.net.callback.IError;
import com.car.core.net.callback.IFailure;
import com.car.core.net.callback.IRequest;
import com.car.core.net.callback.ISuccess;
import com.car.core.net.callback.RequestCallback;
import com.car.core.net.download.DownloadHandler;
import com.car.core.net.lvdata.HttpMethod;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Copyright (C)
 *
 * @file: RestClient
 * @author: 345
 * @Time: 2019/4/17 13:14
 * @description: ${DESCRIPTION}
 */
public class RestClient {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final File FILE;
//    private final Context CONTEXT;

    public RestClient(String url,
                      Map<String, Object> params,
                      String download_dir,
                      String extension,
                      String name,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      File file,
                      Context context) {
        this.URL = url;
        PARAMS.putAll(params);
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.FILE = file;
//        this.CONTEXT = context;
    }

    /**
     * @return 返回一个建造者，来对需要的属性进行初始化
     */
    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }


    private void request(HttpMethod method, String baseUrl) {
        // 获取网络接口请求的实例
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        //判断要进行什么网络请求，然后创建请求的实例
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                /*
                 *  RequestBody Json数据提交：
                 *  FormBody 表单数据提交:
                 *  MultipartBody 文件上传
                 */
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = RestCreator.getRestService().upload(URL, body);
            default:

                break;
        }
        if (call != null) {
            /*
             *  发送请求
             *      同步：调用Call对象的execute()，返回结果的响应体
             *      异步：调用Call对象的enqueue()，参数是一个回调
             */
            //异步请求，传入一个自定义的CallBack ，
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallback(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR
        );
    }

    /**
     * get 请求
     */
    public final void get(String baseUrl) {
        request(HttpMethod.GET,baseUrl);
    }

    /**
     * post 请求
     */
    public final void post(String baseUrl) {
        if (BODY == null) {
            request(HttpMethod.POST,baseUrl);
            return;
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null !");
            }
        }
        request(HttpMethod.POST_RAW,baseUrl);
    }

    public final void put(String baseUrl) {
        if (BODY == null) {
            request(HttpMethod.PUT,baseUrl);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null !");
            }
        }
        request(HttpMethod.PUT_RAW,baseUrl);
    }

    public final void delete(String baseUrl) {
        request(HttpMethod.DELETE,baseUrl);
    }
    public final void upload(String baseUrl){
        request(HttpMethod.UPLOAD,baseUrl);
    }

    public final void download(String baseUrl){
        new DownloadHandler(URL,REQUEST,DOWNLOAD_DIR,EXTENSION,NAME,SUCCESS,FAILURE,ERROR)
                .handleDownload(baseUrl);
    }

}
