package com.car.tabmine.LiveDataRetrofit;

import android.util.Log;

import androidx.lifecycle.LiveData;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * 自定义的响应类型
 */
public class LiveDataCallAdapterFactory extends CallAdapter.Factory {
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {

        //当接口返回类型是 LiveData时，返回自定义的适配器
        if (getRawType(returnType) != LiveData.class) {
            return null;
        }
        Type bodyType = getParameterUpperBound(0, (ParameterizedType) returnType);
        return new LiveDataCallAdapter<>(bodyType);
    }


    /**
     * 转换响应数据类型(Type->R(自定义类型))，并发送自定义的数据
     * @param <R>
     */
    class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<R>> {
        private final Type responseType;

        LiveDataCallAdapter(Type responseType) {
            this.responseType = responseType;
        }

        @Override
        public Type responseType() {
            return responseType;
        }

        /**
         * 嗯，负责传入 Call<R> 并返回。中间你可以做一些转换
         * 这里我们将数据发送出去
         * @param call
         * @return
         */
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
                                Log.e("Demo", "执行" + response.body());
                                R body = response.body();
                                String path = call.request().url().encodedPath();
                                postValue(body);
                            }

                            @Override
                            public void onFailure(Call<R> call, Throwable throwable) {
                            }
                        });
                    }
                }
            };
        }

    }
}
