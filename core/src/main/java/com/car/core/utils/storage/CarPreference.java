package com.car.core.utils.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.car.core.api.BaseUrl;
import com.car.core.api.Const;
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
    private static final String DEFAULT = "default";


    public static SharedPreferences.Editor getAppPreferenceEdit(String s) {
        return Latte.getAppContext().getSharedPreferences(s, 0).edit();
    }

    public static SharedPreferences getAppPreference(String s) {
        return Latte.getAppContext().getSharedPreferences(s, 0);
    }

    public static void setAppFlag(String key, boolean flag) {
        getAppPreferenceEdit("")
                .putBoolean(key, flag)
                .apply();
    }

    public static boolean getAppFlag(String key) {
        return getAppPreference("").getBoolean(key, false);
    }

    public static void setValue(String key, String string) {
        getAppPreferenceEdit(DEFAULT)
                .putString(key, string)
                .apply();
    }

    public static String getValue(String key) {
        return getAppPreference(DEFAULT).getString(key, "");
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


    public static void putUserPhone(String phone) {
        getAppPreferenceEdit(USER)
                .putString("userPhone", phone)
                .commit();
    }

    /**
     * 获取LoginName 用户账号
     *
     * @return 返回用户账号
     */
    public static String getUserPhone() {
        return getAppPreference(USER).getString("userPhone", null);
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
        if (userSex == null || Integer.valueOf(userSex) == 0) {
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
    public static String getUserPhoto() {
        return getAppPreference(USER).getString("userPhoto", null);
    }


    /**
     * 设置用户是否设置支付密码
     *
     * @param userPass
     */
    public static void putUserIsPayPass(int userPass) {
        getAppPreferenceEdit(USER)
                .putInt("PayPass", userPass)
                .commit();
    }

    /**
     * 获取用户是否设置支付密码
     *
     * @return 头像地址
     */
    public static boolean getUserIsPayPass() {
        int payPass = getAppPreference(USER).getInt("PayPass", 0);
        return payPass != 0;
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
     * 获取 cookie
     */
    public static String getCookie() {
        String cookie = getAppPreference(USER).getString("cookie", "");
        if (cookie == null || cookie.isEmpty()) {
            return null;
        } else {
            return cookie;
        }
    }

    /**
     * 设置账户余额
     *
     * @param userMoney 账户余额
     */
    public static void putUserMoney(String userMoney) {
        getAppPreferenceEdit(USER)
                .putString("userMoney", userMoney)
                .commit();
    }

    /**
     * 获取是账户余额
     *
     * @return 账户余额
     */
    public static String getUserMoney() {
        return getAppPreference(USER).getString("userMoney", null);
    }

    /**
     * 设置返现余额
     *
     * @param userMoney 返现余额
     */
    public static void putUserCashBackMoney(String userMoney) {
        getAppPreferenceEdit(USER)
                .putString("cashBackMoney", userMoney)
                .commit();
    }

    /**
     * 获取是返现余额
     *
     * @return 返现余额
     */
    public static String getUserCashBackMoney() {
        return getAppPreference(USER).getString("cashBackMoney", null);
    }


    /**
     * 设置最低提现金额
     *
     * @param cashStartMoney 最低提现金额
     */
    public static void putCashStartMoney(String cashStartMoney) {
        getAppPreferenceEdit(USER)
                .putString("cashStartMoney", cashStartMoney)
                .commit();
    }

    /**
     * 获取最低提现金额
     *
     * @return 最低提现金额
     */
    public static String getCashStartMoney() {
        return getAppPreference(USER).getString("cashStartMoney", null);
    }

    /**
     * 设置最高提现金额
     *
     * @param cashEndMoney 最高提现金额
     */
    public static void putCashEndMoney(String cashEndMoney) {
        getAppPreferenceEdit(USER)
                .putString("cashEndMoney", cashEndMoney)
                .commit();
    }

    /**
     * 获取最高提现金额
     *
     * @return 最高提现金额
     */
    public static String getCashEndMoney() {
        return getAppPreference(USER).getString("cashEndMoney", null);
    }

    /**
     * 设置提现手续费率
     *
     * @param cashRate 手续费
     */
    public static void putCashRate(String cashRate) {
        getAppPreferenceEdit(USER)
                .putString("cashRate", cashRate)
                .commit();
    }

    /**
     * 获取提现手续费率
     *
     * @return 获取提现手续费
     */
    public static String getCashRate() {
        return getAppPreference(USER).getString("cashRate", null);
    }

    /**
     * 保存定位城市
     *
     * @param city 城市
     */
    public static void putCity(String city) {
        getAppPreferenceEdit(USER)
                .putString("city", city)
                .commit();
    }

    /**
     * 获取定位城市，默认西安
     */
    public static String getCity() {
        return getAppPreference(USER).getString("city", BaseUrl.DEFAULTCITY);
    }

    /**
     * 保存经度
     *
     * @param longitude
     */
    public static void putLongitude(String longitude) {
        getAppPreferenceEdit(USER)
                .putString("longitude", longitude)
                .commit();
    }

    /**
     * 获取 经度，默认西安钟楼
     *
     * @param context
     * @return
     */
    public static String getLongitude(Context context) {
        return getAppPreference(USER).getString("longitude", "108.94702");
    }

    /**
     * 保存维度
     *
     * @param latitude
     */
    public static void putLatitude(String latitude) {
        getAppPreferenceEdit(USER)
                .putString("latitude", latitude)
                .commit();
    }

    /**
     * 获取纬度
     *
     * @param context
     * @return
     */
    public static String getLatitude(Context context) {
        return getAppPreference(USER).getString("latitude", "34.259432");
    }

    /**
     * 保存城市代码
     *
     * @param areaId
     */
    public static void putAreaId(String areaId) {
        getAppPreferenceEdit(USER)
                .putString("areaId", areaId)
                .commit();
    }

    /**
     * 获取城市代码
     *
     * @return 默认西安
     */
    public static String getAreaId() {
        return getAppPreference(USER).getString("areaId", BaseUrl.DEFAULTCITYAREAID);
    }
}
