package com.car.tabmine.mine.adapter;

import android.content.Context;

import androidx.appcompat.widget.AppCompatTextView;

import com.car.core.ui.adapter.CarBaseAdapter;
import com.car.core.ui.adapter.ViewHolder;
import com.car.tabmine.R;
import com.car.core.utils.bean.TextStringBean;

import java.util.List;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.adapter
 * @time 2019/10/10 22:35
 * @description
 */
public class GradViewOneAdapter extends CarBaseAdapter<TextStringBean> {
    List<TextStringBean> list;

    public GradViewOneAdapter(List<TextStringBean> list, Context context, int resId) {
        super(list, context, resId);
        this.list = list;
    }

    @Override
    public void setData(ViewHolder viewHolder, int position) {
        TextStringBean textStringBean = list.get(position);
        ((AppCompatTextView) viewHolder.findViewById(R.id.item_tv_tv_amount_tv))
                .setText(String.valueOf(textStringBean.getAmount()));
        ((AppCompatTextView) viewHolder.findViewById(R.id.item_tv_tv_title_tv))
                .setText(textStringBean.getTitle());
    }

    public void addData(List<TextStringBean> list){
        this.list.clear();
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }
}
