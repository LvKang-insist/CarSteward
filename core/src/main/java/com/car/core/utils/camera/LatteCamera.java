package com.car.core.utils.camera;

import android.net.Uri;

import com.car.core.delegate.base.PermissionCheckerDelegate;
import com.car.core.utils.file.FileUtil;

/**
 * Copyright (C)
 *
 * @file: LatteCamera
 * @author: 345
 * @Time: 2019/5/9 9:55
 * @description: 照相机调用类
 */
public class LatteCamera {
    public static Uri createCropFile(){
        return Uri.parse(FileUtil.createFile("crop_image",
                FileUtil.getFileNameByTime("IMG","jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate){
        new CameraHandler(delegate).beginCameraDialog();
    }

}
