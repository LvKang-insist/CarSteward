package com.car.tabhome.home.adapter;

import android.graphics.Color;

import com.car.core.utils.bean.GetStylesBean;
import com.car.core.utils.bean.IndexBean;
import com.car.core.utils.bean.TextImageBean;
import com.car.tabhome.HomeItemType;
import com.car.ui.recycler.DataConverter;
import com.car.ui.recycler.MultipleFields;
import com.car.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabhome.home.adapter
 * @time 2019/11/10 21:37
 * @description
 */
public class HomeConverter extends DataConverter {

    /**
     * 初始化 banner 内容
     */
    List<GetStylesBean.DataBean.AdGallerysBean> adGallerys;

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
                    .setField(MultipleFields.COLOR, 0)
                    .setField(MultipleFields.OBJECT, new TextImageBean(null, TextImage.two[i], null))
                    .build();
            ENTITLES.add(tab);
        }
        if (adGallerys == null) {
            adGallerys = new ArrayList<>();
        }
        MultipleItemEntity banner = MultipleItemEntity.builder()
                .setField(MultipleFields.ITEM_TYPE, HomeItemType.ITEM_HOME_THREE)
                .setField(MultipleFields.SPAN_SIZE, 20)
                .setField(MultipleFields.OBJECT, adGallerys)
                .build();
        ENTITLES.add(banner);

        for (int i = 0; i < 10; i++) {
            MultipleItemEntity tab = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, HomeItemType.ITEM_HOME_FOUR)
                    .setField(MultipleFields.SPAN_SIZE, 4)
                    .setField(MultipleFields.COLOR, 0)
                    .setField(MultipleFields.OBJECT, new TextImageBean(null, TextImage.four[i], null))
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
    }

    public void addStyle(GetStylesBean bean) {
        GetStylesBean.DataBean data = bean.getData();
        int pos = getPos(HomeItemType.ITEM_HOME_TWO);
        int end = getEnd(HomeItemType.ITEM_HOME_THREE);
        if (data.getFontColor() != null && !data.getFontColor().isEmpty()) {
            int color = Color.parseColor("#" + data.getFontColor());
            String[] img = new String[]{data.getWashcarImg(), data.getWashcarImg(),
                    data.getMaintainImg(), data.getArbImg()};
            setData(color, img, pos, end);

            pos = getPos(HomeItemType.ITEM_HOME_FOUR);
            end = getEnd(HomeItemType.ITEM_HOME_FIVE);
            String[] image = new String[]{data.getCheapCarImg(), data.getUsedCarImg(),
                    data.getChangeTireImg(), data.getChangeBottleImg(), data.getChangingGlassImg(),
                    data.getRescueImg(), data.getViolationInquiryImg(), data.getValuationCarImg(),
                    data.getDecorateImg(), data.getMoreImg()};
            setData(color, image, pos, end);
        }
        if (adGallerys != null) {
            adGallerys.clear();
            adGallerys.addAll(data.getAdGallerys());
        } else {
            adGallerys = data.getAdGallerys();
        }
        pos = getPos(HomeItemType.ITEM_HOME_THREE);
        ENTITLES.get(pos).setField(MultipleFields.LIST, adGallerys);
    }

    private void setData(int color, String[] image, int pos, int end) {
        int j = 0;
        for (int i = pos; i < end; i++) {
            ENTITLES.get(i).setField(MultipleFields.COLOR, color);
            TextImageBean textImageBean = ENTITLES.get(i).getField(MultipleFields.OBJECT);
            textImageBean.setImage(image[j]);
            j++;
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
        return getPos(itemType);
    }
}
