package com.car.ui.recycler.rdefault;



import com.car.core.R;
import com.car.ui.recycler.MultipleFields;
import com.car.ui.recycler.MultipleItemEntity;
import com.car.ui.recycler.MultipleItemType;
import com.car.ui.recycler.MultipleRecyclerAdapter;
import com.car.ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.core.ui.recycler.rdefault
 * @time 2019/11/2 20:59
 * @description
 */
public class ListAdapter extends MultipleRecyclerAdapter {
    public ListAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(MultipleItemType.ITEM_LIST, R.layout.item_list);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        holder.setText(R.id.item_list_text, entity.getField(MultipleFields.TEXT));
        holder.setImageResource(R.id.item_list_image, R.drawable.back_right);
    }
}
