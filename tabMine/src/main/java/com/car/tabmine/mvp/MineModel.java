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


    public List<TextIntegerBean> setGvOneData() {
        List<TextIntegerBean> textIntegerBeans = new ArrayList<>();
        TextIntegerBean t1 = new TextIntegerBean("收藏夹", 0);
        TextIntegerBean t2 = new TextIntegerBean("足迹", 0);
        TextIntegerBean t3 = new TextIntegerBean("银行卡", 0);
        TextIntegerBean t4 = new TextIntegerBean("优惠券", 0);
        textIntegerBeans.add(t1);
        textIntegerBeans.add(t2);
        textIntegerBeans.add(t3);
        textIntegerBeans.add(t4);
        return textIntegerBeans;
    }

    public List<TextImageBean> setGvTwoData() {
        List<TextImageBean> textImageBeans = new ArrayList<>();
        TextImageBean p1 = new TextImageBean(R.drawable.daifukuan, "代付款");
        TextImageBean p2 = new TextImageBean(R.drawable.daifahuo, "代发货");
        TextImageBean p3 = new TextImageBean(R.drawable.daishouhuo, "待收货");
        TextImageBean p4 = new TextImageBean(R.drawable.daipingjia, "待评价");
        TextImageBean p5 = new TextImageBean(R.drawable.tuihuo_shouhou, "退货/换货");
        textImageBeans.add(p1);
        textImageBeans.add(p2);
        textImageBeans.add(p3);
        textImageBeans.add(p4);
        textImageBeans.add(p5);
        return textImageBeans;
    }

    public List<TextImageBean> setGvThreeData() {
        List<TextImageBean> textImageBeans = new ArrayList<>();
        TextImageBean p1 = new TextImageBean(R.drawable.gouwuche, "购物车");
        TextImageBean p2 = new TextImageBean(R.drawable.huiyuanzhongxin, "会员中心");
        TextImageBean p22 = new TextImageBean(R.drawable.wodequanyi, "我的权益");
        TextImageBean p3 = new TextImageBean(R.drawable.jifenzhongxin, "积分中心");
        TextImageBean p4 = new TextImageBean(R.drawable.jifenshangcheng, "积分商城");
        TextImageBean p5 = new TextImageBean(R.drawable.wodeqianbao, "我的钱包");
        TextImageBean p6 = new TextImageBean(R.drawable.wodecheku, "我的车库");
        TextImageBean p9 = new TextImageBean(R.drawable.dianzifapiao_person, "电子发票");
        TextImageBean p10 = new TextImageBean(R.drawable.huiyuanrenwu, "会员任务");
        TextImageBean p11 = new TextImageBean(R.drawable.yaoqinghaoyou, "邀请好友");
        textImageBeans.add(p1);
        textImageBeans.add(p2);
        textImageBeans.add(p22);
        textImageBeans.add(p3);
        textImageBeans.add(p4);
        textImageBeans.add(p5);
        textImageBeans.add(p6);
        textImageBeans.add(p9);
        textImageBeans.add(p10);
        textImageBeans.add(p11);
        return textImageBeans;
    }
}
