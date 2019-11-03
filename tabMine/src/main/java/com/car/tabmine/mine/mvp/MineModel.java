package com.car.tabmine.mine.mvp;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.car.core.mvp.model.BaseModel;
import com.car.core.net.lvdata.CarRequest;
import com.car.core.utils.bean.TextImageBean;
import com.car.core.utils.bean.TextStringBean;
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
public class MineModel extends BaseModel {

    public List<TextStringBean> setGvOneData(String[] count) {
        List<TextStringBean> textStringBeans = new ArrayList<>();
        TextStringBean t1 = new TextStringBean("收藏夹", count[0]);
        TextStringBean t2 = new TextStringBean("足迹", count[1]);
        TextStringBean t3 = new TextStringBean("银行卡", count[2]);
        TextStringBean t4 = new TextStringBean("优惠券", count[3]);
        textStringBeans.add(t1);
        textStringBeans.add(t2);
        textStringBeans.add(t3);
        textStringBeans.add(t4);
        return textStringBeans;
    }

    public List<TextImageBean> setGvTwoData(int[] count) {
        List<TextImageBean> textImageBeans = new ArrayList<>();
        TextImageBean p1 = new TextImageBean(R.drawable.daifukuan, "代付款", count[0]);
        TextImageBean p2 = new TextImageBean(R.drawable.daifahuo, "代发货", count[1]);
        TextImageBean p3 = new TextImageBean(R.drawable.daishouhuo, "待收货", count[2]);
        TextImageBean p4 = new TextImageBean(R.drawable.daipingjia, "待评价", count[3]);
        TextImageBean p5 = new TextImageBean(R.drawable.tuihuo_shouhou, "退货/换货", count[4]);
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

    @Override
    public void request(String url, WeakHashMap param, LifecycleOwner owner, Observer observer) {
        CarRequest.result(url, param, liveData -> liveData.observe(owner, observer));
    }
}
