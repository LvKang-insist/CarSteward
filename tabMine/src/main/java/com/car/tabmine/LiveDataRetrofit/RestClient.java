package com.car.tabmine.LiveDataRetrofit;

import android.content.Context;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;


import java.util.ArrayList;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 网络请求框架
 */
public class RestClient {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.instance().getParams();
    private final RequestBody BODY;
    private final String DOWLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final Context CONTEXT;
    private final ArrayList<MultipartBody.Part> BODYS = new ArrayList<>();
    private final FragmentManager MANAGER;

    public RestClient(String url, Map<String, Object> params, RequestBody body, Context context,
                      ArrayList<MultipartBody.Part> bodys, String dowload, String extension,
                      String name, FragmentManager manager) {
        this.URL = url;
        PARAMS.putAll(params);
        this.BODY = body;
        this.CONTEXT = context;
        this.BODYS.addAll(bodys);
        this.DOWLOAD_DIR = dowload;
        this.EXTENSION = extension;
        this.NAME = name;
        this.MANAGER = manager;
    }

    public String getURL() {
        return URL;
    }

    public Map<String, Object> getPARANS() {
        return PARAMS;
    }

    public RequestBody getBODY() {
        return BODY;
    }

    public FragmentManager getMANAGER() {
        return MANAGER;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private LiveData<String> request(HttpMethod method) {
        final RestService service = RestCreator.instance().getService();
        LiveData<String> call = null;
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAN:
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
                call = service.upload(URL, BODYS);
                break;
            case UPLOAD_IMG:
                call = service.uploadImg(URL, BODYS.get(0));
            default:
                break;
        }
        return call;
    }

    public final LiveData<String> get() {
        return request(HttpMethod.GET);
    }

    public final LiveData<String> post() {
        if (BODY == null) {
            return request(HttpMethod.POST);
        } else {
            if (PARAMS.isEmpty()) {
                throw new RuntimeException("params null");
            } else {
                return request(HttpMethod.POST_RAN);
            }
        }
    }

    public final LiveData<String> put() {
        if (BODY == null) {
            return request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params null");
            } else {
                return request(HttpMethod.PUT_RAW);
            }
        }
    }

    public final LiveData<String> delete() {
        return request(HttpMethod.DELETE);
    }

    public final LiveData<String> upload() {
        return request(HttpMethod.UPLOAD);
    }

    public final LiveData<String> uploadImg() {
        return request(HttpMethod.UPLOAD_IMG);
    }

}
