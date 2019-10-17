package com.car.core.net.rx;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.car.core.utils.file.FileUtils;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
     * @param url    url
     * @param key    键
     * @param object 值
     * @return 返回一个被观察着
     */
    public static Observable<String> onGetRxObs(String url, String[] key, Object[] object) {
        WeakHashMap<String, Object> map = new WeakHashMap<>();
        for (int i = 0; i < key.length; i++) {
            map.put(key[i], object[i]);
        }
        return RxRestClient.builder()
                .url(url)
                .params(map)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * @param url      url
     * @param params   get 的参数
     * @param listener 借口，将请求的结果返回出去
     */
    public static void onGetRx(String url, WeakHashMap<String, Object> params, OnRxReqeustListener listener) {
        RxRestClient.builder()
                .url(url)
                .params(params)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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

    /**
     * @param url      url
     * @param key      键
     * @param object   值
     * @param listener 接口口，将请求的结果返回出去
     */
    public static void onGetRx(String url, String[] key, Object[] object, OnRxReqeustListener listener) {
        WeakHashMap<String, Object> map = new WeakHashMap<>();
        for (int i = 0; i < key.length; i++) {
            map.put(key[i], object[i]);
        }
        RxRestClient.builder()
                .url(url)
                .params(map)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
                        listener.onNext(false, e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * @param url      url
     * @param json     post 的参数
     * @param listener 借口，将请求的结果返回出去
     */
    public static void onPostRx(Context context, String url, String json, OnRxReqeustListener listener) {
        RxRestClient.builder()
                .url(url)
                .raw(json)
                .build()
                .post()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
                        listener.onNext(false, e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
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

}
