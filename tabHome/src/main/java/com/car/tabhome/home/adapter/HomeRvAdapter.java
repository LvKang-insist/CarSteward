package com.car.tabhome.home.adapter;

import androidx.recyclerview.widget.GridLayoutManager;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.car.core.api.BaseUrl;
import com.car.core.ui.banner.BannerCreator;
import com.car.core.ui.recycler.MultipleFields;
import com.car.core.ui.recycler.MultipleItemEntity;
import com.car.core.ui.recycler.MultipleRecyclerAdapter;
import com.car.core.ui.recycler.MultipleViewHolder;
import com.car.core.ui.view.CarouselView;
import com.car.core.utils.bean.GetStylesBean;
import com.car.core.utils.bean.IndexBean;
import com.car.core.utils.bean.TextImageBean;
import com.car.core.utils.util.GlideUtil;
import com.car.tabhome.HomeDelegate;
import com.car.tabhome.HomeItemType;
import com.car.tabhome.R;
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
        addItemType(HomeItemType.ITEM_HOME_THREE, R.layout.item_banner);
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
                }
                break;
            case HomeItemType.ITEM_HOME_TWO:
                TextImageBean bean = entity.getField(MultipleFields.OBJECT);
                int color = entity.getField(MultipleFields.COLOR);
                holder.setText(R.id.item_icon_tv_tv, bean.getTitle());
                if (color != 0) {
                    holder.setTextColor(R.id.item_icon_tv_tv, color);
                }
                if (bean.getImage() != null && !bean.getImage().isEmpty()) {
                    GlideUtil.setImage(BaseUrl.BASE_URL + bean.getImage(), holder.getView(R.id.item_icon_tv_icon));
                }
                holder.getView(R.id.mine_item_icon_tv).setOnClickListener(v -> ToastUtils.show(bean.getTitle()));
                break;
            case HomeItemType.ITEM_HOME_THREE:
                List<GetStylesBean.DataBean.AdGallerysBean> adGallerys = entity.getField(MultipleFields.LIST);
                ArrayList<String> image = new ArrayList<>();
                if (adGallerys != null && adGallerys.size() > 0) {
                    for (int i = 0; i < adGallerys.size(); i++) {
                        image.add(BaseUrl.BASE_URL + adGallerys.get(i).getAdFile());
                    }
                } else {
                    image.add(String.valueOf(R.drawable.back));
                }
                ConvenientBanner banner = holder.getView(R.id.item_banner);
                BannerCreator.setDefault(banner, image, position -> {
                    ToastUtils.show(adGallerys.get(position).getAdName());
                });
                break;
            case HomeItemType.ITEM_HOME_FOUR:

                TextImageBean fourBean = entity.getField(MultipleFields.OBJECT);
                int tvColor = entity.getField(MultipleFields.COLOR);
                holder.setText(R.id.item_icon_tv_tv, fourBean.getTitle());
                if (tvColor != 0) {
                    holder.setTextColor(R.id.item_icon_tv_tv, tvColor);
                }
                if (fourBean.getImage() != null && !fourBean.getImage().isEmpty()) {
                    GlideUtil.setImage(BaseUrl.BASE_URL + fourBean.getImage(), holder.getView(R.id.item_icon_tv_icon));
                }
                holder.getView(R.id.mine_item_icon_tv).setOnClickListener(v -> ToastUtils.show(fourBean.getTitle()));
                break;
            case HomeItemType.ITEM_HOME_FIVE:
                IndexBean five_bean = entity.getField(MultipleFields.OBJECT);
                if (five_bean != null) {
                    List<IndexBean.NoticeBean> notice = five_bean.getNotice();
                    CarouselView view = holder.getView(R.id.item_carouse_view);
                    ArrayList list = new ArrayList();
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

    public void bug() {
        ToastUtils.show("已修复");
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }
}
