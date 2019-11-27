package com.car.tabmine.setting;

import com.car.ui.recycler.DataConverter;
import com.car.ui.recycler.MultipleFields;
import com.car.ui.recycler.MultipleItemEntity;
import com.car.ui.recycler.MultipleItemType;

import java.util.ArrayList;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.setting
 * @time 2019/11/2 20:56
 * @description
 */
public class RvConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        String[] list = new String[]{"个人资料", "安全设置", "常用地址管理", "帮助与反馈", "关于我们"};
        for (String s : list) {
            MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, MultipleItemType.ITEM_LIST)
                    .setField(MultipleFields.TEXT, s)
                    .build();
            ENTITLES.add(entity);
        }
        return ENTITLES;
    }
}
