package com.car.tabhome.home.adapter;

import android.graphics.Color;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.car.core.api.BaseUrl;
import com.car.core.ui.recycler.MultipleFields;
import com.car.core.ui.recycler.MultipleItemEntity;
import com.car.core.ui.recycler.MultipleItemType;
import com.car.core.ui.recycler.MultipleRecyclerAdapter;
import com.car.core.ui.recycler.MultipleViewHolder;
import com.car.core.ui.view.CarouselView;
import com.car.core.utils.bean.IndexBean;
import com.car.core.utils.bean.TextImageBean;
import com.car.core.utils.util.GlideUtil;
import com.car.tabhome.HomeDelegate;
import com.car.tabhome.HomeItemType;
import com.car.tabhome.R;
import com.elvishew.xlog.XLog;
import com.hjq.toast.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabhome.home.adapter
 * @time 2019/11/10 15:39
 * @description
 */
public class HomeRvAdapter extends MultipleRecyclerAdapter {

    private HomeDelegate delegate;

    public HomeRvAdapter(List<MultipleItemEntity> data, HomeDelegate delegate) {
        super(data);
        this.delegate = delegate;
        addItemType(HomeItemType.ITEM_HOME_ONE, R.layout.item_home_add_car);
        addItemType(HomeItemType.ITEM_HOME_TWO, R.layout.item_icon_tv);
        addItemType(HomeItemType.ITEM_HOME_THREE, R.layout.item_banner_image);
        addItemType(HomeItemType.ITEM_HOME_FOUR, R.layout.item_icon_tv);
        addItemType(HomeItemType.ITEM_HOME_FIVE, R.layout.item_carouse_view);

    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        switch (holder.getItemViewType()) {
            case HomeItemType.ITEM_HOME_ONE:
                IndexBean indexBean = entity.getField(MultipleFields.OBJECT);
                if (indexBean != null) {
                    holder.setText(R.id.item_home_car_brand, indexBean.getBrandName())
                            .setText(R.id.item_home_car_series, indexBean.getYearsTypeName())
                            .setText(R.id.item_home_add_live_car, "添加爱车");
                    holder.getView(R.id.item_home_add_live_car).setOnClickListener(v -> {
                        ToastUtils.show("添加爱车");
                    });
                }
                break;
            case HomeItemType.ITEM_HOME_TWO:
                TextImageBean bean = entity.getField(MultipleFields.OBJECT);
                holder.setText(R.id.item_icon_tv_tv, bean.getTitle());
                holder.getView(R.id.item_icon_tv_icon).setBackgroundColor(Color.RED);
                if (bean.getImage() != 0) {
                    GlideUtil.setImage(BaseUrl.BASE_URL + bean.getImage(), holder.getView(R.id.item_icon_tv_icon));
                }
                break;
            case HomeItemType.ITEM_HOME_THREE:
                ConvenientBanner banner = holder.getView(R.id.item_banner_image);
                banner.setBackgroundResource(R.color.red);
                break;
            case HomeItemType.ITEM_HOME_FOUR:
                TextImageBean fBean = entity.getField(MultipleFields.OBJECT);
                holder.setText(R.id.item_icon_tv_tv, fBean.getTitle());
                holder.getView(R.id.item_icon_tv_icon).setBackgroundColor(Color.RED);
                if (fBean.getImage() != 0) {
                    GlideUtil.setImage(BaseUrl.BASE_URL + fBean.getImage(), holder.getView(R.id.item_icon_tv_icon));
                }
                break;
            case HomeItemType.ITEM_HOME_FIVE:
                IndexBean five_bean = entity.getField(MultipleFields.OBJECT);
                if (five_bean != null) {
                    List<IndexBean.NoticeBean> notice = five_bean.getNotice();
                    CarouselView view = holder.getView(R.id.item_carouse_view);
                    ArrayList list = new ArrayList();
                    XLog.e("----"+notice.size());
                    for (int i = 0; i < notice.size(); i++) {
                        list.add(notice.get(i).getArticleContent());
                    }
                    view.upDataListAndView(list, 3000);
                    view.startLooping();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }
}
