package com.car.tabmine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.car.tabmine.mvp.TextStringBean;

import java.util.List;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.adapter
 * @time 2019/10/10 22:35
 * @description
 */
public class Adap extends BaseAdapter {
    private final Context context;
    private final int resid;
    List<TextStringBean> list;

    public Adap(List<TextStringBean> list, Context context, int resId) {
        this.context = context;
        this.list = list;
        this.resid = resId;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resid, parent, false);
        }
        return convertView;
    }
}
