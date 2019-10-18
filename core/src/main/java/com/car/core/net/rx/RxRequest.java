package com.car.core.net.rx;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.car.core.utils.file.FileUtils;
import com.car.core.utils.storage.CarPreference;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * 简单的 封装一些RxJava
 */
public class RxRequest {

    public interface OnRxReqeustListener {
        /**
         * @param flag   是否请求成功 true 为成功
         * @param result flag为true，则result为 成功的信息 ，否则为失败的信息
         */
        void onNext(boolean flag, String result);
    }

    public interface OnRxDowloadListener {
        void onNext(boolean flag, File file);
    }


    /**
     * @param url    url
     * @param params get 的参数
     * @return 返回一个被观察着
     */
    public static Observable<String> onGetRxObs(String url, WeakHashMap<String, Object> params) {
        return RxRestClient.builder()
                .url(url)
                .params(params)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     *  get 请求，获取返回的 cookie并保存
     * @param url    url
     * @param params get 的参数
     * @param listener 返回数据的接口
     * @return 返回一个被观察着
     */
    public static void onGetRxCookie(String url, WeakHashMap<String, Object> params, OnRxReqeustListener listener) {
        RxRestClient.builder()
                .url(url)
                .params(params)
                .build()
                .getCookie()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Response<ResponseBody> response) {
                        Headers headers = response.headers();
                        String cookie = headers.get("set-cookie");
                        try {
                            if (cookie!=null && !cookie.isEmpty()){
                                CarPreference.putCookie(cookie.substring(0, cookie.indexOf(";")));
                            }
                            listener.onNext(true, response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onNext(false, null);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public static void onAddCookieRxObs(String url, WeakHashMap<String, Object> params, OnRxReqeustListener listener) {
        Observable<String> stringObservable = RxRestClient.builder()
                .url(url)
                .params(params)
                .cookie(CarPreference.getCookie())
                .build()
                .addCookie()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        onResult(listener,stringObservable);
    }


    /**
     * @param url      url
     * @param params   get 的参数
     * @param listener 借口，将请求的结果返回出去
     */
    public static void onGetRx(String url, WeakHashMap<String, Object> params, OnRxReqeustListener listener) {
        Observable<String> stringObservable = RxRestClient.builder()
                .url(url)
                .params(params)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        onResult(listener, stringObservable);
    }



    /**
     * @param url      url
     * @param json     post 的参数
     * @param listener 借口，将请求的结果返回出去
     */
    public static void onPostRx(Context context, String url, String json, OnRxReqeustListener listener) {
        Observable<String> stringObservable = RxRestClient.builder()
                .url(url)
                .raw(json)
                .build()
                .post()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        onResult(listener,stringObservable);
    }


    /**
     * @param url  url
     * @param json post 的参数
     * @return 返回一个被观察着
     */
    public static Observable<String> onPostRxObs(String url, String json) {
        return RxRestClient.builder()
                .url(url)
                .raw(json)
                .build()
                .post()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static void dowloadFile(String url, @NonNull String dir,
                                   @NonNull String name,
                                   @NonNull OnRxDowloadListener dowloadListener) {
        RxRestClient.builder()
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
                });
    }

    private static void onResult(OnRxReqeustListener listener, Observable<String> stringObservable) {
        stringObservable
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        listener.onNext(true, s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("RxJava ", "onError: " + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
                        listener.onNext(false, null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
