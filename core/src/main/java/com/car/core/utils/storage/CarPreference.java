package com.car.core.utils.storage;

import android.content.SharedPreferences;
import com.car.core.latte.Latte;

/**
 * Copyright (C)
 *
 * @file: LattePreference
 * @author: 345
 * @Time: 2019/4/21 11:25
 * @description:
 */
public class CarPreference {


   public static SharedPreferences.Editor getAppPreferenceEdit(String s){
       return Latte.getContext().getSharedPreferences(s,0).edit();
   }

   public static SharedPreferences getAppPreference(String s){
       return Latte.getContext().getSharedPreferences(s,0);
   }

    public static void setAppFlag(String key,boolean flag){
        getAppPreferenceEdit("")
                .putBoolean(key,flag)
                .apply();
    }

    public static boolean getAppFlag(String key){
        return getAppPreference("").getBoolean(key,false);
    }


    /**
     *  sp get 方法
     */
    public static String getCustomAppProfile(String key) {
        return getAppPreference("").getString(key,"");
    }

    /**
     * 保存是否登陆
     * @param isLongin true 为登录
     */
    public static void putLogin( Boolean isLongin){
        getAppPreferenceEdit("user")
        .putBoolean("isLogin", isLongin)
        .commit();
    }

    /**
     * 获取是否登陆
     * @return true 表示登录
     */
    public static boolean getLogin(){
        return getAppPreference("user").getBoolean("isLogin", false);
    }
}
