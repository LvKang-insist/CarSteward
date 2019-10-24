package com.car.tabmine.mvp;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabmine.mvp
 * @time 2019/10/11 21:59
 * @description
 */
public class TextStringBean {
    private String title;
    private String amount;

    public TextStringBean(String title, String amount) {
        this.title = title;
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
