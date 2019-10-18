package com.car.core.utils.storage;

import android.content.Context;
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

    private static final String USER = "user";


    public static SharedPreferences.Editor getAppPreferenceEdit(String s) {
        return Latte.getContext().getSharedPreferences(s, 0).edit();
    }

    public static SharedPreferences getAppPreference(String s) {
        return Latte.getContext().getSharedPreferences(s, 0);
    }

    public static void setAppFlag(String key, boolean flag) {
        getAppPreferenceEdit("")
                .putBoolean(key, flag)
                .apply();
    }

    public static boolean getAppFlag(String key) {
        return getAppPreference("").getBoolean(key, false);
    }


    /**
     * sp get 方法
     */
    public static String getCustomAppProfile(String key) {
        return getAppPreference("").getString(key, "");
    }

    /**
     * 保存是否登陆
     *
     * @param isLongin true 为登录
     */
    public static void putLogin(Boolean isLongin) {
        getAppPreferenceEdit(USER)
                .putBoolean("isLogin", isLongin)
                .commit();
    }

    /**
     * 获取是否登陆
     *
     * @return true 表示登录
     */
    public static boolean getLogin() {
        return getAppPreference(USER).getBoolean("isLogin", false);
    }


    /**
     * 保存tokenId
     *
     * @param tokenId tokenId
     */
    public static void putTokenId(String tokenId) {
        getAppPreferenceEdit(USER).
                putString("tokenId", tokenId)
                .commit();
    }

    /**
     * 获取TokenId
     *
     * @return 返回 tokenid，
     */
    public static String getTokenId() {
        return getAppPreference(USER).getString("tokenId", null);
    }

    /**
     * 保存用户账号
     *
     * @param loginName 账号
     */
    public static void putLoginName(String loginName) {
        getAppPreferenceEdit(USER)
                .putString("loginName", loginName)
                .commit();
    }

    /**
     * 获取LoginName 用户账号
     *
     * @return 返回用户账号
     */
    public static String getLoginName() {
        return getAppPreference(USER).getString("loginName", null);
    }

    /**
     * 保存用户昵称
     *
     * @param userName 昵称
     */
    public static void putUserName(String userName) {
        getAppPreferenceEdit(USER)
                .putString("userName", userName)
                .commit();
    }

    /**
     * 获取用户 昵称
     *
     * @return 用户昵称
     */
    public static String getUserName() {
        return getAppPreference(USER).getString("userName", null);
    }

    /**
     * 保存性别 userSex
     *
     * @param userSex（0：保密， 1：男 ，2：女）
     */
    public static void putUserSex(String userSex) {
        String sex;
        if (Integer.valueOf(userSex) == 0) {
            sex = "保密";
        } else if (Integer.valueOf(userSex) == 1) {
            sex = "男";
        } else {
            sex = "女";
        }
        getAppPreferenceEdit(USER)
                .putString("userSex", sex)
                .commit();
    }

    /**
     * 获取用户性别
     *
     * @return 性别
     */
    public static String getUserSex() {
        return getAppPreference(USER).getString("userSex", null);
    }

    /**
     * 保存 头像
     *
     * @param userPhoto 头像地址
     */
    public static void putUserPhoto(String userPhoto) {
        if (userPhoto == null) {
            userPhoto = "";
        }
        getAppPreferenceEdit(USER)
                .putString("userPhoto", userPhoto)
                .commit();
    }

    /**
     * 获取用户头像
     *
     * @return 头像地址
     */
    public static String getUserPhotox() {
        return getAppPreference(USER).getString("userPhoto", null);
    }

    /**
     * 保存 qq 状态
     *
     * @param isQq 1 为绑定qq
     */
    public static void putQq(String isQq) {
        boolean flag = false;
        if (Integer.valueOf(isQq) == 1) {
            flag = true;
        }
        getAppPreferenceEdit(USER)
                .putBoolean("isBD", flag)
                .commit();
    }

    /**
     * 是否绑定QQ
     *
     * @return true 为绑定，否则不绑定
     */
    public static Boolean isQq(Context context) {
        return getAppPreference(USER).getBoolean("isBD", false);
    }


    /**
     * 保存微信状态
     *
     * @param isWecht 1 为绑定微信
     */
    public static void putWechat(String isWecht) {
        boolean flag = false;
        if (Integer.valueOf(isWecht) == 1) {
            flag = true;
        }
        getAppPreferenceEdit(USER)
                .putBoolean("isWX", flag)
                .commit();
    }

    /**
     * 是否绑定微信
     *
     * @return true 为绑定，否则不绑定
     */
    public static Boolean isWechat(Context context) {
        return getAppPreference(USER).getBoolean("isWX", false);
    }

    /**
     * 设置个人信息是否修改
     *
     * @param isFirstEnter true 为修改，否则不修改
     */
    public static void putUserInfoIsRevise(boolean isFirstEnter) {
        getAppPreferenceEdit(USER)
                .putBoolean("isReviser", isFirstEnter)
                .commit();
    }

    /**
     * 获取个人信息是否修改
     *
     * @return true 为修改，否则不修改
     */
    public static Boolean isUserInfoIsRevise() {
        return getAppPreference(USER).getBoolean("isReviser", false);
    }

    /**
     * 设置首页是否修改
     *
     * @param isFirstEnter true 为 修改，否则不修改
     */
    public static void putHomeIsRevise(boolean isFirstEnter) {
        getAppPreferenceEdit(USER)
                .putBoolean("isHomeReviser", isFirstEnter)
                .commit();
    }

    /**
     * 设置首页是否修改
     *
     * @return true 为修改，否则不修改
     */
    public static Boolean isHomeIsRevise() {
        return getAppPreference(USER).getBoolean("isHomeReviser", false);
    }

    /**
     * 保存 cookie
     *
     * @param cookie cookie
     */
    public static void putCookie(String cookie) {
        getAppPreferenceEdit(USER)
                .putString("cookie", cookie)
                .commit();
    }

    /**
     * 设置首页是否修改
     *
     */
    public static String getCookie() {
        String cookie = getAppPreference(USER).getString("cookie", "");
        if (cookie == null || cookie.isEmpty()) {
            return null;
        } else {
            return cookie;
        }
    }
}
