package com.car.core.utils.camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;

import com.car.core.delegate.base.PermissionCheckerDelegate;
import com.car.core.latte.Latte;
import com.car.core.utils.file.FileUtil;
import com.elvishew.xlog.XLog;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.car.core.utils.camera.RequestCode.CROP_ERROR;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.core.utils.camera
 * @time 2019/11/6 21:33
 * @description
 */
public class CropPhoto {

    public static void cropPhoto(PermissionCheckerDelegate delegate, boolean fromCapture, Uri uri) {
        Uri imageUrl;
        //打开系统自带的裁剪图片的intent
        Intent intent = new Intent("com.android.camera.action.CROP");
        // 注意一定要添加该项权限，否则会提示无法裁剪
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("scale", true);
        // 取消人脸识别
        intent.putExtra("noFaceDetection", true);
        // 图片输出格式
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        // 若为false则表示不返回数据
        intent.putExtra("return-data", false);

        // 指定裁剪完成以后的图片所保存的位置,pic info显示有延时
        if (fromCapture) {
            // 如果是使用拍照，那么原先的uri和最终目标的uri一致,注意这里的uri必须是Uri.fromFile生成的
            imageUrl = Uri.fromFile(CameraImageBean.getInstance().getFile());
        } else {
            //获取一个 名字,
            final String currentPhotoName = getPhotoName();
            //创建一个文件，路径为系统相册，第二个参数为名字
            final File tempFile = new File(FileUtil.CAMERA_PHOTO_DIR, currentPhotoName);
            // 从相册中选择，那么裁剪的图片保存在take_photo中
            if (!tempFile.getParentFile().exists()) {
                tempFile.getParentFile().mkdirs();
            }
            imageUrl = Uri.fromFile(tempFile);
        }
        CameraImageBean.getInstance().setPath(imageUrl);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUrl);
        // 以广播方式刷新系统相册，以便能够在相册中找到刚刚所拍摄和裁剪的照片
        Intent intentBc = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intentBc.setData(uri);
        Latte.getBaseMvpActivity().sendBroadcast(intentBc);
        delegate.startActivityForResult(intent, CROP_ERROR);
    }

    private static String getPhotoName() {
        return FileUtil.getFileNameByTime("IMG", "jpg");
    }
}
