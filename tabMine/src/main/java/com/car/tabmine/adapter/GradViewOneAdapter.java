package com.car.tabmine.adapter;

import android.content.Context;

import androidx.appcompat.widget.AppCompatTextView;

import com.car.core.ui.adapter.CarBaseAdapter;
import com.car.core.ui.adapter.ViewHolder;
import com.car.tabmine.R;
import com.car.tabmine.mvp.TextImageBean;
import com.car.tabmine.mvp.TextIntegerBean;

import java.util.List;

import cn.bingoogolapple.badgeview.BGABadgeImageView;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.adapter
 * @time 2019/10/10 22:35
 * @description
 */
public class GradViewOneAdapter extends CarBaseAdapter<TextIntegerBean> {
    List<TextIntegerBean> list;

    public GradViewOneAdapter(List<TextIntegerBean> list, Context context, int resId) {
        super(list, context, resId);
        this.list = list;
    }

    @Override
    public void setData(ViewHolder viewHolder, int position) {
        TextIntegerBean textIntegerBean = list.get(position);
        ((AppCompatTextView) viewHolder.findViewById(R.id.item_tv_tv_amount_tv))
                .setText(String.valueOf(textIntegerBean.getAmount()));
        ((AppCompatTextView) viewHolder.findViewById(R.id.item_tv_tv_title_tv))
                .setText(textIntegerBean.getTitle());
    }
}
