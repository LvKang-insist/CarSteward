package com.car.core.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.adapter
 * @time 2019/10/10 22:35
 * @description
 */

public abstract class CarBaseAdapter<T> extends BaseAdapter {

    /** 封装有数据的数据源*/
    private List<T> list;
    private Context context;
    /** listview item 的资源 id*/
    private int resId;

    public CarBaseAdapter(List<T> list, Context context, int resId) {
        this.list = list;
        this.context = context;
        this.resId = resId;
    }

    @Override
    public int getCount() {
        return list!=null?list.size():0;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.getHolder(view,viewGroup,context,resId);
        // 需要显示内容
        setData(holder,i);
        return holder.getmConvertView();
    }
    public abstract void setData(ViewHolder viewHolder,int position);
}
