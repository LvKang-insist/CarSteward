package com.car.core.net.rx;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.car.core.utils.storage.CarPreference;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * @author 345
 * 简单的 封装一些RxJava
 */
public class CarRequest {

    public interface OnReqeustListener {
        /**
         * 直接为 主线程
         *
         * @param liveData 网络请求的结果
         */
        void onNext(LiveData<String> liveData);
    }

    public interface OnResponseListener {
        /**
         * 直接为 主线程
         *
         * @param liveData 网络请求的响应体
         */
        void onNext(LiveData<Response> liveData);
    }

    public interface OnRxDowloadListener {
        /**
         * 下载接口
         *
         * @param flag
         * @param file
         */
        void onNext(boolean flag, File file);
    }


    /**
     * get 请求，获取返回的 cookie并保存
     *
     * @param url      url
     * @param params   get 的参数
     * @param listener 返回数据的接口
     * @return 返回一个被观察着
     */
    public static void getCookie(String url, WeakHashMap<String, Object> params, OnResponseListener listener) {
        listener.onNext(RxRestClient.builder()
                .url(url)
                .params(params)
                .cookie(CarPreference.getCookie())
                .build()
                .getCookie());
    }

    public static void onAddCookieRxObs(String url, WeakHashMap<String, Object> params, OnReqeustListener listener) {
        onResult(listener, RxRestClient.builder()
                .url(url)
                .params(params)
                .cookie(CarPreference.getCookie())
                .build()
                .addCookie());
    }


    /**
     * @param url      url
     * @param params   get 的参数
     * @param listener 借口，将请求的结果返回出去
     */
    public static void result(String url, WeakHashMap<String, Object> params, OnReqeustListener listener) {
        onResult(listener, RxRestClient.builder()
                .url(url)
                .params(params)
                .build()
                .get());

    }


    /**
     * @param url      url
     * @param json     post 的参数
     * @param listener 借口，将请求的结果返回出去
     */
    public static void onPostRx(String url, String json, OnReqeustListener listener) {
        onResult(listener, RxRestClient.builder()
                .url(url)
                .raw(json)
                .build()
                .post());
    }


    public static void dowloadFile(String url, @NonNull String dir,
                                   @NonNull String name,
                                   @NonNull OnRxDowloadListener dowloadListener) {
        /*RxRestClient.builder()
                .url(url)
                .build()
                .download()
                .subscribeOn(Schedulers.io())
                .map(responseBody -> {
                    InputStream inputStream = responseBody.byteStream();
                    return FileUtils.writeToDisk(inputStream, dir, name);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<File>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(File file) {
                        dowloadListener.onNext(true, file);
                    }

                    @Override
                    public void onError(Throwable e) {
                        dowloadListener.onNext(true, null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
    }

    private static void onResult(OnReqeustListener listener, LiveData<String> liveData) {
        if (listener != null) {
            listener.onNext(liveData);
        }
    }
}
