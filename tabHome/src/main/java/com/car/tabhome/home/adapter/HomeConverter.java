package com.car.tabhome.home.adapter;

import com.car.core.ui.recycler.DataConverter;
import com.car.core.ui.recycler.MultipleItemEntity;
import com.car.core.utils.bean.IndexBean;

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
        return ENTITLES;
    }

    public void add(IndexBean bean) {

    }
}
