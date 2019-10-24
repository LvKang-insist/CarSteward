package com.car.core.utils.util;

import android.content.Context;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.car.core.latte.Latte;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.core.utils.util
 * @time 2019/10/24 21:43
 * @description
 */
public class GlideUtil {
    public static void setImage(String url, ImageView imageView) {
        Glide.with(Latte.getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }
}
