package com.car.tabmall.mall.adapter;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;

import com.car.core.api.BaseUrl;
import com.car.core.utils.bean.GetGoodsListBean;
import com.car.core.utils.util.GlideUtil;
import com.car.tabmall.MallItemType;
import com.car.tabmall.R;
import com.car.ui.recycler.MultipleFields;
import com.car.ui.recycler.MultipleItemEntity;
import com.car.ui.recycler.MultipleRecyclerAdapter;
import com.car.ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmall.mall.adapter
 * @time 2019/11/8 20:58
 * @description
 */
public class MallRvAdapter extends MultipleRecyclerAdapter {

    public MallRvAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(MallItemType.ITEM_MALL_LIST, R.layout.item_mall_shop_list);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        GetGoodsListBean.DataBean dataBean = entity.getField(MultipleFields.OBJECT);
        holder.setText(R.id.item_mall_shop_list_name_tv, dataBean.getGoodsName())
                .setText(R.id.item_mall_shop_list_price_tv, dataBean.getShopPrice());
        AppCompatImageView image = holder.getView(R.id.item_mall_shop_list_iv);
        GlideUtil.setImage(BaseUrl.BASE_URL + dataBean.getGoodsThums(), image);
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }

}
