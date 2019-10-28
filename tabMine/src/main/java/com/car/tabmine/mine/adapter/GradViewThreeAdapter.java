package com.car.tabmine.mine.adapter;

import android.content.Context;

import androidx.appcompat.widget.AppCompatTextView;

import com.car.core.ui.adapter.CarBaseAdapter;
import com.car.core.ui.adapter.ViewHolder;
import com.car.tabmine.R;
import com.car.tabmine.mine.mvp.TextImageBean;

import java.util.List;

import cn.bingoogolapple.badgeview.BGABadgeImageView;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.adapter
 * @time 2019/10/10 22:35
 * @description
 */
public class GradViewThreeAdapter extends CarBaseAdapter<TextImageBean> {
    List<TextImageBean> list;

    public GradViewThreeAdapter(List<TextImageBean> list, Context context, int resId) {
        super(list, context, resId);
        this.list = list;
    }

    @Override
    public void setData(ViewHolder viewHolder, int position) {
        TextImageBean textImageBean = list.get(position);
        ((BGABadgeImageView) viewHolder.findViewById(R.id.item_icon_tv_icon))
                .setImageResource(textImageBean.getImage());
        ((AppCompatTextView) viewHolder.findViewById(R.id.item_icon_tv_tv))
                .setText(textImageBean.getTitle());
    }
}
