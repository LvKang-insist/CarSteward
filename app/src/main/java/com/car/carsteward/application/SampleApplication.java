package com.car.carsteward.application;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * @author 345 QQ:1831712732
 * @name TestDemo
 * @class name：com.car.testdemo
 * @time 2019/11/24 19:08
 * @description
 */
public class SampleApplication extends TinkerApplication {
    public SampleApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.car.carsteward.application.SampleApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }
}

