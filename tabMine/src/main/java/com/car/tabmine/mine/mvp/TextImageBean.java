package com.car.tabmine.mine.mvp;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.mvp
 * @time 2019/10/10 23:04
 * @description
 */
public class TextImageBean {
    private int image;
    private String title;
    private int bga;

    public TextImageBean(int imageId, String title) {
        this.image = imageId;
        this.title = title;
    }

    public TextImageBean(int image, String title, int bga) {
        this.image = image;
        this.title = title;
        this.bga = bga;
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
}
