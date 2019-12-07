package com.car.core.utils.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.core.utils.util
 * @time 2019/11/29 21:03
 * @description
 */
public class BusinessScopeList {

    public String id;
    public String name;
    private static List<BusinessScopeList> businessScopeList;
    private static List<String> names;
    private static BusinessScopeList mInstance;

    private BusinessScopeList(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public static List<BusinessScopeList> getBusinessScopeList() {
        if (businessScopeList == null) {
            businessScopeList = new ArrayList<>();
        }
        businessScopeList.clear();
        businessScopeList.add(new BusinessScopeList("1", "美容"));
        businessScopeList.add(new BusinessScopeList("2", "改装"));
        businessScopeList.add(new BusinessScopeList("3", "装潢"));
        businessScopeList.add(new BusinessScopeList("4", "洗车"));
        businessScopeList.add(new BusinessScopeList("5", "保养"));
        businessScopeList.add(new BusinessScopeList("6", "换轮胎"));
        businessScopeList.add(new BusinessScopeList("7", "换电瓶"));
        businessScopeList.add(new BusinessScopeList("8", "救援"));
        businessScopeList.add(new BusinessScopeList("15", "换玻璃"));
        businessScopeList.add(new BusinessScopeList("14", "补胎"));
        businessScopeList.add(new BusinessScopeList("12", "电子产品加装"));
        return businessScopeList;
    }

    public static List<String> getBusinessScopeNames() {
        if (names == null) {
            names = new ArrayList<>();
        }
        names.clear();
        names.add("美容");
        names.add("改装");
        names.add("装潢");
        names.add("洗车");
        names.add("保养");
        names.add("换轮胎");
        names.add("换电瓶");
        names.add("救援");
        names.add("换玻璃");
        names.add("补胎");
        names.add("电子产品加装");
        return names;
    }
}
