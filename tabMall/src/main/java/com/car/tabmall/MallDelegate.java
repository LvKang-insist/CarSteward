package com.car.tabmall;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.car.core.api.Const;
import com.car.core.delegate.BottomItemDelegate;
import com.car.core.latte.Latte;
import com.car.core.mvp.factory.CreatePresenter;
import com.car.core.mvp.mvpdefault.DefaultContract;
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl;
import com.car.core.ui.recycler.MultipleFields;
import com.car.core.ui.view.CustomGridManager;
import com.car.core.utils.bean.GetGoodsListBean;
import com.car.core.utils.util.RequestParam;
import com.car.core.utils.util.UrlParam;
import com.car.tabmall.mall.adapter.MallConverter;
import com.car.tabmall.mall.adapter.MallRvAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.elvishew.xlog.XLog;
import com.hjq.toast.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmall
 * @time 2019/10/7 22:56
 * @description 商城
 */
@CreatePresenter(DefaultPresenterImpl.class)
public class MallDelegate extends BottomItemDelegate<DefaultPresenterImpl>
        implements DefaultContract.IDefaultView, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R2.id.delegate_mall_et)
    AppCompatEditText mEdit = null;
    @BindView(R2.id.delegate_mall_recycler)
    RecyclerView mRecycler = null;
    @BindView(R2.id.delegate_mall_refresh)
    SwipeRefreshLayout mRefresh = null;
    private GetGoodsListBean listBean;
    private MallConverter converter;
    private MallRvAdapter adapter;
    private boolean isSearch = false;

    @OnClick(R2.id.delegate_mall_search_iv)
    void search() {
        String content = mEdit.getText().toString();
        if (content.isEmpty()) {
            ToastUtils.show("请输入商品内容");
            return;
        }
        getPresenter().request(Const.API_MALL + UrlParam.getParam("getGoodsList"),
                RequestParam.builder()
                        .addParam("p", "1")
                        .addParam("keyWords", content)
                        .addParam("goodsSrc", "1").build());
        isSearch = true;
    }

    @Override
    public Object setLayout() {
        return R.layout.mall_delegate;
    }

    @Override
    public void bindView(View view) {
        mRecycler.setLayoutManager(new CustomGridManager(getContext(), 2));
        mRefresh.setOnRefreshListener(this);
        converter = new MallConverter();
        adapter = new MallRvAdapter(converter.convert());
        adapter.setOnLoadMoreListener(this, mRecycler);
        adapter.setOnItemClickListener(this);
        mRecycler.setAdapter(adapter);
    }

    @Override
    public int getToolbar() {
        return R.id.delegate_mall_toolbar;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        Latte.showLoading("");
        getShop("1");
    }


    @Override
    public void onResult(String result) {
        listBean = gson.fromJson(result, GetGoodsListBean.class);
        if (listBean.getStatus() != 1) {
            ToastUtils.show(listBean.getMsg());
            return;
        }
        if (mRefresh.isRefreshing() || isSearch) {
            converter.clear();
            mRefresh.setRefreshing(false);
        }
        converter.add(listBean);
        adapter.notifyItemChanged(converter.size() - listBean.getData().size(), converter.size());
        adapter.loadMoreComplete();
        Latte.stopLoading();
    }

    /**
     * 上拉加载
     */
    @Override
    public void onLoadMoreRequested() {
        if (listBean.getCurrPage() == listBean.getTotalPage()) {
            adapter.loadMoreEnd();
            return;
        }
        getShop(String.valueOf(listBean.getCurrPage() + 1));
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        getShop("1");
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        GetGoodsListBean.DataBean bean = this.adapter.getData().get(position).getField(MultipleFields.OBJECT);
        ToastUtils.show(bean.getGoodsName());
    }


    private void getShop(String page) {
        getPresenter().request(Const.API_MALL + UrlParam.getParam("getGoodsList"),
                RequestParam.builder()
                        //当前页数 1
                        .addParam("p", page)
                        //0 积分，1 自营
                        .addParam("goodsSrc", "1").build());
    }


}
