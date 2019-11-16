package com.car.tabhome.home.adapter;

import android.graphics.Color;

import com.car.core.ui.recycler.DataConverter;
import com.car.core.ui.recycler.MultipleFields;
import com.car.core.ui.recycler.MultipleItemEntity;
import com.car.core.ui.recycler.MultipleItemType;
import com.car.core.utils.bean.GetStylesBean;
import com.car.core.utils.bean.IndexBean;
import com.car.core.utils.bean.TextImageBean;
import com.car.tabhome.HomeItemType;
import com.elvishew.xlog.XLog;

import java.util.ArrayList;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabhome.home.adapter
 * @time 2019/11/10 21:37
 * @description
 */
public class HomeConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {

        MultipleItemEntity entity = MultipleItemEntity.builder()
                .setField(MultipleFields.ITEM_TYPE, HomeItemType.ITEM_HOME_ONE)
                .setField(MultipleFields.SPAN_SIZE, 20)
                .setField(MultipleFields.OBJECT, null)
                .build();
        ENTITLES.add(entity);

        for (int i = 0; i < 4; i++) {
            MultipleItemEntity tab = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, HomeItemType.ITEM_HOME_TWO)
                    .setField(MultipleFields.SPAN_SIZE, 5)
                    .setField(MultipleFields.OBJECT, new TextImageBean(0, TextImage.two[i], null))
                    .build();
            ENTITLES.add(tab);
        }
        MultipleItemEntity banner = MultipleItemEntity.builder()
                .setField(MultipleFields.ITEM_TYPE, HomeItemType.ITEM_HOME_THREE)
                .setField(MultipleFields.SPAN_SIZE, 20)
                .setField(MultipleFields.OBJECT, null)
                .build();
        ENTITLES.add(banner);

        MultipleItemEntity banner1 = MultipleItemEntity.builder()
                .setField(MultipleFields.ITEM_TYPE, HomeItemType.ITEM_HOME_THREE)
                .setField(MultipleFields.SPAN_SIZE, 20)
                .setField(MultipleFields.OBJECT, null)
                .build();
        ENTITLES.add(banner1);

        MultipleItemEntity banner2 = MultipleItemEntity.builder()
                .setField(MultipleFields.ITEM_TYPE, HomeItemType.ITEM_HOME_THREE)
                .setField(MultipleFields.SPAN_SIZE, 20)
                .setField(MultipleFields.OBJECT, null)
                .build();
        ENTITLES.add(banner2);

        for (int i = 0; i < 10; i++) {
            MultipleItemEntity tab = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, HomeItemType.ITEM_HOME_FOUR)
                    .setField(MultipleFields.SPAN_SIZE, 4)
                    .setField(MultipleFields.OBJECT, new TextImageBean(0, TextImage.four[i], null))
                    .build();
            ENTITLES.add(tab);
        }

        MultipleItemEntity headLine = MultipleItemEntity.builder()
                .setField(MultipleFields.ITEM_TYPE, HomeItemType.ITEM_HOME_FIVE)
                .setField(MultipleFields.SPAN_SIZE, 20)
                .setField(MultipleFields.OBJECT, null)
                .build();
        ENTITLES.add(headLine);
        return ENTITLES;
    }

    public void add(IndexBean bean) {
        int pos = getPos(HomeItemType.ITEM_HOME_ONE);
        ENTITLES.get(pos)
                .setField(MultipleFields.OBJECT, bean);

        pos = getPos(HomeItemType.ITEM_HOME_FIVE);
        ENTITLES.get(pos)
                .setField(MultipleFields.OBJECT, bean);
        XLog.e(ENTITLES.size());
    }

    public void addStyle(GetStylesBean bean) {
        GetStylesBean.DataBean data = bean.getData();
        int pos = getPos(HomeItemType.ITEM_HOME_TWO);
        int end = getEnd(HomeItemType.ITEM_HOME_THREE);
        if (data.getFontColor() != null && !data.getFontColor().isEmpty()) {
            int color = Color.parseColor("#" + data.getFontColor());
            for (int i = pos; i < end; i++) {
                ENTITLES.get(i).setField(MultipleFields.COLOR, color);
            }
        }
    }


    private int getPos(int itemType) {
        for (int i = 0; i < ENTITLES.size(); i++) {
            int type = ENTITLES.get(i).getField(MultipleFields.ITEM_TYPE);
            if (type == itemType) {
                return i;
            }
        }
        throw new NullPointerException("没有找到对应的 ITEM_TYPE ");
    }

    private int getEnd(int itemType) {
        return getPos(itemType) - 1;
    }
}
