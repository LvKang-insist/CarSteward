package com.lv.moduletabmine.application

import android.app.Application
import com.car.core.latte.Latte

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.lv.moduletabmine.application
 * @time 2020/4/20 20:35
 * @description
 */
class MineApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //自定义初始化
        Latte.init(this)
                .withToastUtils()
                .withLog()
                .configure()
    }
}