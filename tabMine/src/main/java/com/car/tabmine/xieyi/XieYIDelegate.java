package com.car.tabmine.xieyi;

import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.car.core.api.Const;
import com.car.core.delegate.web.WebDelegateImpl;
import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.mvp.mvpdefault.DefaultContract;
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl;
import com.car.core.mvp.view.BaseMvpFragment;
import com.car.tabmine.R;
import com.car.tabmine.R2;
import java.util.WeakHashMap;
import butterknife.BindView;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.login
 * @time 2019/10/20 15:58
 * @description
 */
@CreatePresenter(DefaultPresenterImpl.class)
public class XieYIDelegate extends BaseMvpFragment<DefaultPresenterImpl>
        implements DefaultContract.IDefaultView {

    @BindView(R2.id.toolbar_title)
    AppCompatTextView mToolbarTitle = null;
    private WebDelegateImpl mWebDelegate;
    private final String xieyi = "&a=getClauseAgreement";

    @Override
    public Object setLayout() {
        return R.layout.delegate_xieyi;
    }

    @Override
    public void bindView(View view) {
        initToolbar();
        initWeb();
        WeakHashMap<String, Object> weak = new WeakHashMap<>();
        weak.put("catId", 5);
        getPresenter().request(Const.API_BASE_URL_PUBLIC + xieyi, weak);
    }

    private void initWeb() {

    }

    private void initToolbar() {
        mToolbarTitle.setText("服务用户协议");
        mToolbarTitle.setTextColor(getResources().getColor(R.color.black));
    }

    @Override
    public int getToolbar() {
        return R.id.toolbar;
    }

    @Override
    public boolean onBackPressedSupport() {
        return mWebDelegate.isWebBack();
    }

    @Override
    public void onResult(String result) {
        GetClauseAgreementBean bean = gson.fromJson(result, GetClauseAgreementBean.class);
        mWebDelegate = WebDelegateImpl.create(null);
        mWebDelegate.setTopDelegate(getParentDelegate());
        mWebDelegate.setData(bean.getData().get(0).getArticleContent());
        getSupportDelegate().loadRootFragment(R.id.xiexi_container, mWebDelegate);
    }
}
