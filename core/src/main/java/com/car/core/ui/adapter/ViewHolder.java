package com.car.core.ui.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.adapter
 * @time 2019/10/10 22:35
 * @description
 */

public class ViewHolder {
    /**
     * 用于返回给 ListView adapter getview 方法的view
     */
    private View mConvertView;
    /**
     * 推荐使用这个来代替HashMap<integer,E>
     */
    private SparseArray<View> viewSparseArray = new SparseArray<>();

    public View getmConvertView(){
        return mConvertView;
    }

    public ViewHolder(Context context, int resId) {
        mConvertView = LayoutInflater.from(context).inflate(resId,null);
        //给 view 设置tag
        mConvertView.setTag(this);
    }

    public static ViewHolder getHolder(View convertView,Context context,int resId){
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder(context,resId);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return  viewHolder;
    }
    public View findViewById(int id){
        View view = viewSparseArray.get(id);
        if (view == null) {
            view = mConvertView.findViewById(id);
            viewSparseArray.append(id,view);
        }
        return mConvertView.findViewById(id);
    }
}
