package com.car.tabmall.mall.adapter;

import com.car.core.utils.bean.GetGoodsListBean;
import com.car.tabmall.MallItemType;
import com.car.ui.recycler.DataConverter;
import com.car.ui.recycler.MultipleFields;
import com.car.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;


/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmall.mall.adapter
 * @time 2019/11/8 21:05
 * @description 商城 list 的数据
 */
public class MallConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        return ENTITLES;
    }

    public void add(GetGoodsListBean listBean) {
        final int size = listBean.getData().size();
        for (int i = 0; i < size; i++) {
            MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.SPAN_SIZE, 1)
                    .setField(MultipleFields.ITEM_TYPE, MallItemType.ITEM_MALL_LIST)
                    .setField(MultipleFields.OBJECT, listBean.getData().get(i))
                    .build();
            ENTITLES.add(entity);
        }
    }


}
