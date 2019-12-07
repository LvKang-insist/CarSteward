package com.car.ui.delegate.shoplist.adapter;

import android.content.Context;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.car.core.net.callback.IFailure;
import com.car.ui.R;
import com.car.ui.adapter.CarBaseAdapter;
import com.car.ui.adapter.ViewHolder;
import com.hjq.toast.ToastUtils;

import java.util.List;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.ui.delegate.shoplist.adapter
 * @time 2019/11/28 21:26
 * @description
 */
public class DefaultSelectAdapter extends CarBaseAdapter<String> {
    private List<String> list;

    public DefaultSelectAdapter(List<String> list, Context context, int resId) {
        super(list, context, resId);
        this.list = list;
    }

    @Override
    public void setData(ViewHolder viewHolder, int position) {
        AppCompatTextView textView = (AppCompatTextView) viewHolder.findViewById(R.id.item_tv_tv);
        textView.setText(list.get(position));
        textView.setOnClickListener(v -> ToastUtils.show(list.get(position)));
        if (position == list.size() - 1) {
            viewHolder.findViewById(R.id.item_tv_view).setVisibility(View.GONE);
        } else {
            viewHolder.findViewById(R.id.item_tv_view).setVisibility(View.INVISIBLE);
        }
    }
}
