package com.car.core.ui.banner;

import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.car.core.R;
import com.car.core.utils.util.GlideUtil;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.core.ui.banner
 * @time 2019/11/11 21:05
 * @description
 */
class ImageHolder extends Holder<String> {

    AppCompatImageView image;

    public ImageHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        image = itemView.findViewById(R.id.item_banner_image);
    }

    @Override
    public void updateUI(String url) {
        GlideUtil.setImage(url, image);
    }

}
