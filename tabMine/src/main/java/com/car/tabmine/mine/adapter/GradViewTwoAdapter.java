package com.car.tabmine.mine.adapter;

import android.content.Context;

import androidx.appcompat.widget.AppCompatTextView;

import com.car.core.ui.adapter.CarBaseAdapter;
import com.car.core.ui.adapter.ViewHolder;
import com.car.tabmine.R;
import com.car.core.utils.bean.TextImageBean;

import java.util.List;

import cn.bingoogolapple.badgeview.BGABadgeImageView;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.adapter
 * @time 2019/10/10 22:35
 * @description
 */
public class GradViewTwoAdapter extends CarBaseAdapter<TextImageBean> {
    List<TextImageBean> list;

    public GradViewTwoAdapter(List<TextImageBean> list, Context context, int resId) {
        super(list, context, resId);
        this.list = list;
    }

    @Override
    public void setData(ViewHolder viewHolder, int position) {
        TextImageBean textImageBean = list.get(position);
        BGABadgeImageView image = (BGABadgeImageView) viewHolder.findViewById(R.id.item_icon_tv_icon);
        image.setImageResource(textImageBean.getImage());
        if (textImageBean.getBga() > 0) {
            image.showTextBadge(String.valueOf(textImageBean.getBga()));
        }
        ((AppCompatTextView) viewHolder.findViewById(R.id.item_icon_tv_tv))
                .setText(textImageBean.getTitle());
    }

    public void addData(List<TextImageBean> list) {
        if (list != null) {
            this.list.clear();
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void clear() {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setBga(0);
        }
        notifyDataSetChanged();
    }
}
