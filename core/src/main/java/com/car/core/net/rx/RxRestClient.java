package com.car.core.net.rx;

import com.car.core.net.HttpMethod;
import com.car.core.net.RestCreator;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Copyright (C)
 *
 * @file: RestClient
 * @author: 345
 * @Time: 2019/4/17 13:14
 * @description: ${DESCRIPTION}
 */
public class RxRestClient {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final RequestBody BODY;
    private final File FILE;
    private final String COOKIE;

    public RxRestClient(String url,
                        Map<String, Object> params,
                        RequestBody body,
                        File file,
                        String mCookie) {
        this.URL = url;
        PARAMS.putAll(params);
        this.BODY = body;
        this.FILE = file;
        this.COOKIE = mCookie;
    }

    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();
    }

    private Observable<String> request(HttpMethod method) {
        final RxRestService service = RestCreator.getRxRestService();
        Observable<String> observable = null;
        switch (method) {
            case GET:
                observable = service.get(URL, PARAMS);
                break;
            case ADD_COOKIE:
                observable = service.addCookie(COOKIE,URL,PARAMS);
                break;
            case POST:
                observable = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                observable = service.postRaw(URL, BODY);
                break;
            case PUT:
                observable = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                observable = service.putRaw(URL, BODY);
                break;
            case DELETE:
                observable = service.delete(URL, PARAMS);
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
                observable = service.upload(URL, body);
            default:
                break;
        }
        return observable;
    }

    private Observable<Byte> request() {
        final RxRestService service = RestCreator.getRxRestService();
        return service.getImage(URL, PARAMS);
    }

    public final Observable<String> get() {
        return request(HttpMethod.GET);
    }
    public final Observable<String> addCookie() {
        return request(HttpMethod.ADD_COOKIE);
    }

    /**
     * 获取cookie 的请求
     *
     * @return
     */
    public final Observable<Response<ResponseBody>> getCookie() {
        final RxRestService service = RestCreator.getRxRestService();
        return service.getCookie(URL, PARAMS);
    }


    public final Observable<String> post() {
        if (BODY == null) {
            return request(HttpMethod.POST);
        } else {
           /* if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null !");
            }*/
            return request(HttpMethod.POST_RAW);
        }
    }

    public final Observable<String> put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null !");
            }
        }
        return request(HttpMethod.PUT_RAW);
    }

    public final Observable<String> delete() {
        return request(HttpMethod.DELETE);
    }

    public final Observable<String> upload() {
        return request(HttpMethod.UPLOAD);
    }

    public final Observable<ResponseBody> download() {
        return RestCreator.getRxRestService().download(URL, PARAMS);
    }

}
