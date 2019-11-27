package com.car.tabshop;

import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.car.core.delegate.BottomItemDelegate;
import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.mvp.mvpdefault.DefaultContract;
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl;
import com.car.core.utils.storage.CarPreference;
import com.car.core.utils.util.BusinessScope;
import com.car.ui.delegate.shoplist.BaseShopListDelegate;
import com.hjq.toast.ToastUtils;

import butterknife.BindView;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabshop
 * @time 2019/10/7 22:59
 * @description
 */
@CreatePresenter(DefaultPresenterImpl.class)
public class ShopDelegate extends BottomItemDelegate<DefaultPresenterImpl>
        implements DefaultContract.IDefaultView {

    @Override
    public void onResult(String result) {

    }

    @Override
    public Object setLayout() {
        return R.layout.shop_delegate;
    }

    @Override
    public void bindView(View view) {
        getSupportDelegate().loadRootFragment(R.id.delegate_shop_layout,
                BaseShopListDelegate.newInstance(CarPreference.getMyCar(), BusinessScope.BUSINESSSCOPE_SHOP_LIST));
    }
}
