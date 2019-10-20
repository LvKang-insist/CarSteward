package com.car.tabmine.LiveDataRetrofit.test;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.car.tabmine.LiveDataRetrofit.BaseViewModel;
import com.car.tabmine.LiveDataRetrofit.RestClient;


/**
 * Created by Petterp
 * on 2019-10-19
 * Function: Name ViewModel
 * <p>
 * 注意： 请将 LiveData 更新的UI的 ViewModel 对象存储在对象中，而不是活动或者片段：
 * 避免 activities 臃肿。view只负责显示数据
 * <p>
 * onCreate方法中观察 LitveData 对象，并确保不会在 onResume方法中进行多余调用
 * 当 生命周期处于 onStart时，应用会从 LiveData 正在观察的对象中接收最新值。(注意：仅当 LiveData 已设置要观察的对象时，才会发生这种情况)
 * 更新的情况有以下 ： 从 非活动-> 活动状态，仅有一次；  数据更改时 -> 通知数据更新
 */
public class TestViewModel extends BaseViewModel<String> {

//    private TestSearce searce = RestCreator.instance().getRetrofitClient().create(TestSearce.class);

//    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
       /* getSourceLiveData().observe(owner, s -> {
            Log.e("Demo", s);
        });*/

    }

    public void getBaidu() {
        LiveData<String> data = RestClient.builder().url("https://www.baidu.com/").build().get();

        getSourceLiveData().setSource(data);

        data.observeForever(new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });
    }


}
