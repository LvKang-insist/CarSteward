package com.car.carsteward.application;

import android.annotation.SuppressLint;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.carsteward.application
 * @time 2019/11/24 0:06
 * @description
 */
@SuppressLint("Registered")
public class SampleApplication extends TinkerApplication {
    public SampleApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "xxx.xxx.SampleApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }
}
