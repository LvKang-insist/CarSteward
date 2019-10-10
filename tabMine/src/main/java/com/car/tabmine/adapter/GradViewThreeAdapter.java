package com.car.tabmine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.car.core.ui.adapter.CarBaseAdapter;
import com.car.core.ui.adapter.ViewHolder;
import com.car.tabmine.R;
import com.car.tabmine.mvp.FunctionItemBean;

import java.util.List;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.adapter
 * @time 2019/10/10 22:35
 * @description
 */
public class GradViewThreeAdapter extends CarBaseAdapter<FunctionItemBean> {
    List<FunctionItemBean> list;

    public GradViewThreeAdapter(List<FunctionItemBean> list, Context context, int resId) {
        super(list, context, resId);
        this.list = list;
    }

    @Override
    public void setData(ViewHolder viewHolder, int position) {
        FunctionItemBean functionItemBean = list.get(position);
        ((AppCompatImageView) viewHolder.findViewById(R.id.item_icon_tv_icon))
                .setImageResource(functionItemBean.getImage());
        ((AppCompatTextView) viewHolder.findViewById(R.id.item_icon_tv_tv))
                .setText(functionItemBean.getTitle());
    }
}
