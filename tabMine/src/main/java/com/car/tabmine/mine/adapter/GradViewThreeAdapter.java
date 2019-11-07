package com.car.tabmine.mine.adapter;

import android.content.Context;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.car.core.mvp.view.BaseMvpDelegate;
import com.car.core.ui.adapter.CarBaseAdapter;
import com.car.core.ui.adapter.ViewHolder;
import com.car.core.utils.strategy.BaseStrategySkip;
import com.car.core.utils.strategy.BaseStrategySkipContext;
import com.car.tabmine.R;
import com.car.core.utils.bean.TextImageBean;
import com.hjq.toast.ToastUtils;

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
    private final BaseStrategySkipContext skipContext;

    /**
     * @param list     数据
     * @param resId    layout
     * @param delegate 当前显示的界面
     */
    public GradViewThreeAdapter(List<TextImageBean> list, int resId, BaseMvpDelegate delegate) {
        super(list, delegate.getContext(), resId);
        this.list = list;
        skipContext = BaseStrategySkipContext.newInstance(delegate);
    }

    @Override
    public void setData(ViewHolder viewHolder, int position) {
        TextImageBean textImageBean = list.get(position);
        ((BGABadgeImageView) viewHolder.findViewById(R.id.item_icon_tv_icon))
                .setImageResource(textImageBean.getImage());
        ((AppCompatTextView) viewHolder.findViewById(R.id.item_icon_tv_tv))
                .setText(textImageBean.getTitle());
        viewHolder.findViewById(R.id.mine_item_icon_tv).setOnClickListener(v -> {
            skipContext.setStrategySkip(list.get(position)).skip();
            ToastUtils.show(list.get(position).getTitle());
        });
    }
}
