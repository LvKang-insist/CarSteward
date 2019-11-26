package com.car.ui.banner;

import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.car.core.R;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.core.ui.banner
 * @time 2019/11/11 21:04
 * @description
 */
public class HolderCreate implements CBViewHolderCreator {
    @Override
    public Holder createHolder(View itemView) {
        return new ImageHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_banner_image;
    }
}