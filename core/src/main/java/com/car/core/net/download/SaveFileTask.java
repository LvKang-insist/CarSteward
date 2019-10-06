package com.car.core.net.download;

import android.content.Intent;
import android.os.AsyncTask;

import com.car.core.file.FileUtils;
import com.car.core.latte.Latte;
import com.car.core.net.callback.IRequest;
import com.car.core.net.callback.ISuccess;
import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Copyright (C)
 *
 * @file: SaveFileTask
 * @author: 345
 * @Time: 2019/4/19 16:50
 * @description: ${DESCRIPTION}
 */
public class SaveFileTask extends AsyncTask<Object, Void, File> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;


    public SaveFileTask(IRequest request, ISuccess success) {
        this.REQUEST = request;
        this.SUCCESS = success;
    }

    @Override
    protected File doInBackground(Object... params) {
        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        final ResponseBody body = (ResponseBody) params[2];
        final String name = (String) params[3];
        final InputStream is = body.byteStream();

        if (downloadDir == null || downloadDir.equals("")) {
            downloadDir = "down_loads";
        }
        if (extension == null || extension.equals("")) {
            extension = "";
        }

        if (name == null){
            return FileUtils.writeToDisk(is,downloadDir,extension.toUpperCase(),extension);
        }else {
            return FileUtils.writeToDisk(is,downloadDir,name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS != null){
            SUCCESS.onSuccess(file.getPath());
        }
        if (REQUEST != null){
            REQUEST.onRequestEnd();
        }
    }

    //安装apk
    private void autoInstallApk(File file){
        if (FileUtils.getExtension(file.getPath()).equals("apk")){
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            Latte.getContext().startActivity(install);
        }
    }

}
