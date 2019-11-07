package com.car.core.utils.camera;

import android.net.Uri;

import java.io.File;

/**
 * Copyright (C)
 *
 * @file: CameraImageBean
 * @author: 345
 * @Time: 2019/5/9 9:56
 * @description: 存储一些中间值
 */
public final class CameraImageBean {

    private Uri mPath = null;
    private File file = null;

    private CameraImageBean() {
    }

    private static final CameraImageBean INSTANCE = new CameraImageBean();

    public static CameraImageBean getInstance() {
        return INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri mPath) {
        this.mPath = mPath;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
