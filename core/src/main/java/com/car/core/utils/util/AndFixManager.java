package com.car.core.utils.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.File;
import java.io.IOException;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.core.utils.util
 * @time 2019/11/13 20:29
 * @description
 */
public class AndFixManager {

    /**
     * 文件后缀名
     */
    private static final String FILE_END = ".apatch";
    /**
     * 文件路径
     */
    private String mPatchDir;

    private static AndFixManager mInstance = null;
    private PatchManager mPatchManger = null;


    public static AndFixManager getInstance() {
        if (mInstance == null) {
            synchronized (AndFixManager.class) {
                if (mInstance == null) {
                    mInstance = new AndFixManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化 AndFix 方法
     *
     * @param context
     */
    public void initPatch(Context context) {
        mPatchManger = new PatchManager(context);
        mPatchManger.init(getVersionName(context));
        mPatchManger.loadPatch();
    }


    /**
     * 加载 path 文件
     *
     * @param path
     */
    public void addPath(String path) {
        if (mPatchManger != null) {
            try {
                mPatchManger.addPatch(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取版本号
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        String version = "1.0.0";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            version = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 构造文件名
     *
     * @return
     */
    private String getPatchName() {
        return mPatchDir.concat("car").concat(FILE_END);
    }

}
