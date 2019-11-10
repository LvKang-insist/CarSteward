package com.car.tabhome.home.adapter;

import com.car.core.ui.recycler.MultipleItemEntity;
import com.car.core.ui.recycler.MultipleRecyclerAdapter;
import com.car.core.ui.recycler.MultipleViewHolder;
import com.car.tabhome.HomeItemType;
import com.car.tabhome.R;

import java.util.List;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabhome.home.adapter
 * @time 2019/11/10 15:39
 * @description
 */
public class HomeRvAdapter extends MultipleRecyclerAdapter {

    public HomeRvAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(HomeItemType.ITEM_HOME_ONE, R.layout.item_home_add_car);
        addItemType(HomeItemType.ITEM_HOME_TWO, R.layout.item_icon_tv);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {

    }
}
