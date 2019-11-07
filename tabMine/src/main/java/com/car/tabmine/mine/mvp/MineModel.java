package com.car.tabmine.mine.mvp;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.car.core.mvp.model.BaseModel;
import com.car.core.mvp.view.BaseMvpDelegate;
import com.car.core.net.lvdata.CarRequest;
import com.car.core.utils.bean.TextImageBean;
import com.car.core.utils.bean.TextStringBean;
import com.car.tabmine.R;
import com.car.tabmine.item.shopcart.ShopCartDelegate;

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
        String[] name = new String[]{"代付款", "代发货", "待收货", "待评价", "退货/换货"};
        int[] drawId = new int[]{R.drawable.daifukuan, R.drawable.daifahuo,
                R.drawable.daishouhuo, R.drawable.daipingjia,
                R.drawable.tuihuo_shouhou};
        BaseMvpDelegate[] delegates = new BaseMvpDelegate[]{
                ShopCartDelegate.create(),
                ShopCartDelegate.create(),
                ShopCartDelegate.create(),
                ShopCartDelegate.create(),
                ShopCartDelegate.create()};

        for (int i = 0; i < count.length; i++) {
            textImageBeans.add(new TextImageBean(drawId[i], name[i], count[i], delegates[i]));
        }
        return textImageBeans;
    }

    public List<TextImageBean> setGvThreeData() {
        String[] name = new String[]{"购物车", "会员中心", "我的权益", "积分中心", "积分商城",
                "我的钱包", "我的车库", "电子发票", "会员任务", "邀请好友"};
        int[] drawId = new int[]{R.drawable.gouwuche, R.drawable.huiyuanzhongxin,
                R.drawable.wodequanyi, R.drawable.jifenzhongxin,
                R.drawable.jifenshangcheng, R.drawable.wodeqianbao,
                R.drawable.wodecheku, R.drawable.dianzifapiao_person,
                R.drawable.huiyuanrenwu, R.drawable.yaoqinghaoyou};
        List<TextImageBean> textImageBeans = new ArrayList<>();
        for (int i = 0; i < name.length; i++) {
            textImageBeans.add(new TextImageBean(drawId[i], name[i], ShopCartDelegate.create()));
        }
        return textImageBeans;
    }

    @Override
    public void request(String url, WeakHashMap param, LifecycleOwner owner, Observer observer) {
        CarRequest.result(url, param, liveData -> liveData.observe(owner, observer));
    }
}
