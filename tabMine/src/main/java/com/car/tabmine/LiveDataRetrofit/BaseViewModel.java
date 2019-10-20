package com.car.tabmine.LiveDataRetrofit;


import androidx.lifecycle.ViewModel;

/**
 * Created by Petterp
 * on 2019-10-20
 * Function: ViewModel 集合类
 */
public abstract class BaseViewModel<T> extends ViewModel  {



    private SingleSourceLiveData<T> sourceLiveData;

    public SingleSourceLiveData<T> getSourceLiveData() {
        if (sourceLiveData == null) {
            sourceLiveData = new SingleSourceLiveData<>();
        }
        return sourceLiveData;
    }

}
