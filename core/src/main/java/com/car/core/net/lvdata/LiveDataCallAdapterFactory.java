package com.car.core.net.lvdata;

import androidx.lifecycle.LiveData;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.car.core.latte.Latte;
import com.car.core.net.lvdata.CustomResponse;
import com.elvishew.xlog.XLog;
import com.hjq.toast.ToastUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @author 345
 * 自定义的响应类型
 */
public class LiveDataCallAdapterFactory extends CallAdapter.Factory {
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        //当接口返回类型是 LiveData时，返回自定义的适配器
        if (getRawType(returnType) == LiveData.class) {
            Type bodyType = getParameterUpperBound(0, (ParameterizedType) returnType);
            if (bodyType == String.class) {
                return new LiveDataCallAdapter<>(bodyType);
            } else if (bodyType == CustomResponse.class) {
                return new LiveDataResponseAdapter<>(bodyType);
            }
        }
        return null;
    }


    class LiveDataResponseAdapter<R> implements CallAdapter<R, LiveData<CustomResponse>> {
        private final Type responseType;

        LiveDataResponseAdapter(Type responseType) {
            this.responseType = responseType;
        }

        @Override
        public Type responseType() {
            return responseType;
        }

        @Override
        public LiveData<CustomResponse> adapt(Call<R> call) {
            return new LiveData<CustomResponse>() {
                //原子更新的boolean
                AtomicBoolean started = new AtomicBoolean(false);

                @Override
                protected void onActive() {
                    if (started.compareAndSet(false, true)) {
                        call.enqueue(new Callback<R>() {
                            @Override
                            public void onResponse(Call<R> call, Response<R> response) {
                                XLog.e("request # " + response.raw().request().url());
                                CustomResponse body = (CustomResponse) response.body();
                                if (body != null) {
                                    body.setResponse(response);
                                    postValue(body);
                                }
                            }

                            @Override
                            public void onFailure(Call<R> call, Throwable t) {
                                XLog.e("Request onFailure : " + t.getMessage());
                                postValue(null);
                            }
                        });
                    }
                }
            };
        }
    }

    class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<R>> {
        private final Type responseType;

        LiveDataCallAdapter(Type responseType) {
            this.responseType = responseType;
        }

        @Override
        public Type responseType() {
            return responseType;
        }

        @Override
        public LiveData<R> adapt(final Call<R> call) {
            return new LiveData<R>() {
                //原子更新的boolean
                AtomicBoolean started = new AtomicBoolean(false);

                @Override
                protected void onActive() {
                    super.onActive();
                    //开始观察
                    if (started.compareAndSet(false, true)) {
                        call.enqueue(new Callback<R>() {
                            @Override
                            public void onResponse(Call<R> call, Response<R> response) {
                                R body = response.body();
                                XLog.e("request # " + response.raw().request().url());
                                if (VerifyResult.startVerify((String) body)) {
                                    postValue(body);
                                } else {
                                    postValue(null);
                                    Latte.stopLoading();
                                }
                            }

                            @Override
                            public void onFailure(Call<R> call, Throwable t) {
                                XLog.e("Request onFailure : " + t.getMessage());
                                postValue(null);
                                Latte.stopLoading();
                            }
                        });
                    }
                }
            };
        }

    }
}
