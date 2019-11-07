package com.car.core.utils.bean;

import com.car.core.mvp.view.BaseMvpDelegate;
import com.car.core.utils.strategy.BaseStrategySkip;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.mvp
 * @time 2019/10/10 23:04
 * @description bean 类，实现了策略的业务逻辑：进行跳转
 */
public class TextImageBean implements BaseStrategySkip {
    private int image;
    private String title;
    private int bga;
    private BaseMvpDelegate newDelegate;

    public TextImageBean(int imageId, String title, BaseMvpDelegate newDelegate) {
        this.image = imageId;
        this.title = title;
        this.newDelegate = newDelegate;
    }

    public TextImageBean(int image, String title, int bga, BaseMvpDelegate newDelegate) {
        this.image = image;
        this.title = title;
        this.bga = bga;
        this.newDelegate = newDelegate;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBga() {
        return bga;
    }

    public void setBga(int bga) {
        this.bga = bga;
    }

    @Override
    public void onSkip(BaseMvpDelegate skipDelegate) {
        skipDelegate.parentfragmentAnimStart(newDelegate);
    }

}
