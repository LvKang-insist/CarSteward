package com.car.tabmine.mvp;

import androidx.lifecycle.MutableLiveData;

import com.car.core.mvp.model.BaseModel;
import com.car.tabmine.R;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.mvp
 * @time 2019/10/10 22:50
 * @description
 */
public class MineModel extends BaseModel<String> {
    @Override
    public MutableLiveData request(String url, WeakHashMap param) {
        return null;
    }

    public List<FunctionItemBean> setGvThreeData(){
        List<FunctionItemBean> functionItemBeans = new ArrayList<>();
        FunctionItemBean p1 = new FunctionItemBean(R.drawable.gouwuche, "购物车");
        FunctionItemBean p2 = new FunctionItemBean(R.drawable.huiyuanzhongxin, "会员中心");
        FunctionItemBean p22 = new FunctionItemBean(R.drawable.wodequanyi, "我的权益");
        FunctionItemBean p3 = new FunctionItemBean(R.drawable.jifenzhongxin, "积分中心");
        FunctionItemBean p4 = new FunctionItemBean(R.drawable.jifenshangcheng, "积分商城");
        FunctionItemBean p5 = new FunctionItemBean(R.drawable.wodeqianbao, "我的钱包");
        FunctionItemBean p6 = new FunctionItemBean(R.drawable.wodecheku, "我的车库");
        FunctionItemBean p9 = new FunctionItemBean(R.drawable.dianzifapiao_person, "电子发票");
        FunctionItemBean p10 = new FunctionItemBean(R.drawable.huiyuanrenwu, "会员任务");
        FunctionItemBean p11 = new FunctionItemBean(R.drawable.yaoqinghaoyou, "邀请好友");
        functionItemBeans.add(p1);
        functionItemBeans.add(p2);
        functionItemBeans.add(p22);
        functionItemBeans.add(p3);
        functionItemBeans.add(p4);
        functionItemBeans.add(p5);
        functionItemBeans.add(p6);
        functionItemBeans.add(p9);
        functionItemBeans.add(p10);
        functionItemBeans.add(p11);
        return functionItemBeans;
    }
}
