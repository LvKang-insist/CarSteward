package com.car.tabmine.LiveDataRetrofit;


import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 建造者
 */
public class RestClientBuilder {
    private String mUrl = null;
    private static Map<String, Object> mParams = RestCreator.instance().getParams();
    private static Gson gson = new Gson();
    private RequestBody mBody = null;
    private Context mcontext = null;
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;

    //新增错误码监测工具
    private Context context;
    private FragmentManager fragmentManager;

    private ArrayList<MultipartBody.Part> mbodys = new ArrayList<>();

    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        mParams = params;
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        mParams.put(key, value);
        return this;
    }


    /**
     * 传入原始数据
     *
     * @param
     * @return
     */
    public final RestClientBuilder raw(HashMap<String, Object> map) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), gson.toJson(map));
        return this;
    }

    /**
     * 简单数据直接传
     *
     * @return
     */
    public final RestClientBuilder raw() {
        String s = gson.toJson(mParams);
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), s);
        return this;
    }



    public final RestClientBuilder file(String url, String name) {
        File file = new File(url);
        final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), file);
        final MultipartBody.Part body = MultipartBody.Part.createFormData(name, file.getName(), requestBody);
        mbodys.add(body);
        return this;
    }

    public final RestClientBuilder file(File file, String name) {
        final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), file);
        final MultipartBody.Part body = MultipartBody.Part.createFormData(name, file.getName(), requestBody);
        mbodys.add(body);
        return this;
    }

    public final RestClientBuilder fileImg(File file, String name) {
        final RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);//表单类型
        final MultipartBody.Part body = MultipartBody.Part.createFormData("file", name, requestBody);
        mbodys.add(body);
        return this;
    }

    /**
     * 文件下载存放位置
     *
     * @param idir
     * @return
     */
    public final RestClientBuilder dir(String idir) {
        this.mDownloadDir = idir;
        return this;
    }


    public final RestClientBuilder extension(String ietension) {
        this.mExtension = ietension;
        return this;
    }

    public final RestClientBuilder name(String iname) {
        this.mName = iname;
        return this;
    }


    public final RestClient build() {
        return new RestClient(mUrl, mParams,mBody, mcontext, mbodys, mDownloadDir, mExtension, mName, fragmentManager);
    }


}
