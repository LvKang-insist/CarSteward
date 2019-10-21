package com.car.core.mvp.mvpdefault;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.car.core.mvp.model.BaseModel;
import com.car.core.mvp.presenter.BasePresenter;
import com.car.core.mvp.view.BaseMvpFragment;

import java.util.WeakHashMap;

/**
 * @author 345 QQ:1831712732
 * @name MvpFrame
 * @class name：com.latte.core.mvp.mvpdefault
 * @time 2019/9/10 21:55
 * @description P层的默认实现，如果fragment或者activity中没有过多的数据需要处理，则可以直接使用这个p层
 */
public class DefaultPresenterImpl extends BasePresenter<DefaultContract.IDefaultView, DefaultModel>
        implements DefaultContract.IDefaultPresenter {

    @Override
    protected DefaultModel attachModel() {
        return new DefaultModel();
    }

    @Override
    public void request( String url, WeakHashMap param) {
        getModel().request(url, param, (LifecycleOwner) getView(), (Observer<String>) s -> getView().onResult(s));
    }
}
