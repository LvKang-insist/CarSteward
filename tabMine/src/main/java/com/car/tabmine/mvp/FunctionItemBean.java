package com.car.tabmine.mvp;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.mvp
 * @time 2019/10/10 23:04
 * @description
 */
public class FunctionItemBean {
    private int image;
    private String title;


    public FunctionItemBean(int imageId, String title) {
        this.image = imageId;
        this.title = title;

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


}
