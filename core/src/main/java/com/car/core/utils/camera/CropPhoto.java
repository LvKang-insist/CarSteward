package com.car.core.utils.camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.car.core.delegate.base.PermissionCheckerDelegate;
import com.car.core.latte.Latte;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.core.utils.camera
 * @time 2019/11/6 21:33
 * @description
 */
public class CropPhoto {

    public static void cropPhoto(Activity activity, boolean fromCapture, Uri uri, Uri cropUrie) {
        //打开系统自带的裁剪图片的intent
        Intent intent = new Intent("com.android.camera.action.CROP");
        // 注意一定要添加该项权限，否则会提示无法裁剪
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        intent.setDataAndType(uri, "image/*");
        intent.putExtra("scale", true);

        // 设置裁剪区域的宽高比例
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//
//        // 设置裁剪区域的宽度和高度
//        intent.putExtra("outputX", 200);
//        intent.putExtra("outputY", 200);

        // 取消人脸识别
        intent.putExtra("noFaceDetection", true);
        // 图片输出格式
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        // 若为false则表示不返回数据
        intent.putExtra("return-data", false);

        // 指定裁剪完成以后的图片所保存的位置,pic info显示有延时
        if (fromCapture) {
            // 如果是使用拍照，那么原先的uri和最终目标的uri一致,注意这里的uri必须是Uri.fromFile生成的
//            mCutUri = Uri.fromFile(imgFile);
            cropUrie = FileProvider.getUriForFile(activity, activity.getApplicationContext().getPackageName() + ".provider", new File(Environment.getExternalStorageDirectory() + "/take_photo"));
        } else { // 从相册中选择，那么裁剪的图片保存在take_photo中
            String time = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA).format(new Date());
            String fileName = "photo_" + time;
            File mCutFile = new File(Environment.getExternalStorageDirectory() + "/take_photo", fileName + ".jpeg");
            if (!mCutFile.getParentFile().exists()) {
                mCutFile.getParentFile().mkdirs();
            }
            cropUrie = Uri.fromFile(mCutFile);
//            cropUrie = FileProvider.getUriForFile(activity, activity.getApplicationContext().getPackageName() + ".provider", mCutFile);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropUrie);
//        // 以广播方式刷新系统相册，以便能够在相册中找到刚刚所拍摄和裁剪的照片
        Intent intentBc = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intentBc.setData(uri);
        activity.sendBroadcast(intentBc);

        activity.startActivityForResult(intent, 0); //设置裁剪参数显示图片至ImageVie
    }

}
