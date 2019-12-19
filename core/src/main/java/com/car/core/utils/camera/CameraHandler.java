package com.car.core.utils.camera;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;

import com.car.core.R;
import com.car.core.delegate.base.PermissionCheckerDelegate;
import com.blankj.utilcode.util.FileUtils;
import com.car.core.utils.file.FileUtil;
import com.elvishew.xlog.XLog;

import java.io.File;

import static com.car.core.utils.camera.RequestCode.TAKE_PHOTO;

/**
 * Copyright (C)
 *
 * @file: CameraHandler
 * @author: 345
 * @Time: 2019/5/9 9:54
 * @description: 照片处理类
 */
public class CameraHandler implements View.OnClickListener {
    private final AlertDialog DIALOG;
    private final PermissionCheckerDelegate DELEGATE;

    CameraHandler(PermissionCheckerDelegate delegate) {
        this.DELEGATE = delegate;
        this.DIALOG = new AlertDialog.Builder(delegate.getContext()).create();
    }

    final void beginCameraDialog() {
        DIALOG.show();
        final Window window = DIALOG.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_camera_panel);
            window.setGravity(Gravity.BOTTOM);
            //设置动画
            window.setWindowAnimations(R.style.BottomAnimStyle);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            params.dimAmount = 0.5f;
            window.setAttributes(params);

            window.findViewById(R.id.photodialog_btn_cancel).setOnClickListener(this);
            window.findViewById(R.id.photodialog_btn_native).setOnClickListener(this);
            window.findViewById(R.id.photodialog_btn_take).setOnClickListener(this);
        }
    }

    private String getPhotoName() {
        return FileUtil.getFileNameByTime("IMG", "jpg");
    }

    private void takePhoto() {
        //获取一个 名字,
        final String currentPhotoName = getPhotoName();
        //拍照意图
        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //创建一个文件，路径为系统相册，第二个参数为名字
        final File tempFile = new File(FileUtil.CAMERA_PHOTO_DIR, currentPhotoName);

        Uri imageUri;
        // 注意7.0及以上与之前获取的uri不一样了，返回的是provider路径
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            imageUri = FileProvider.getUriForFile(DELEGATE.getContext(),
                    "com.car.carsteward.fileProvider", tempFile);
        } else {
            imageUri = Uri.fromFile(tempFile);
        }

        CameraImageBean.getInstance().setPath(imageUri);
        CameraImageBean.getInstance().setFile(tempFile);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        DELEGATE.startActivityForResult(intent, TAKE_PHOTO);

        if (DIALOG != null) {
            DIALOG.cancel();
        }
    }

    /**
     * 打开 选择图片
     */
    private void pickPhoto() {
        final Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        //打开相册
        DELEGATE.startActivityForResult(intent, RequestCode.PICK_PHOTO);
        if (DIALOG != null) {
            DIALOG.cancel();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.photodialog_btn_take) {
            takePhoto();
        } else if (id == R.id.photodialog_btn_native) {
            pickPhoto();
        } else if (id == R.id.photodialog_btn_cancel) {
            DIALOG.cancel();
        }
    }
}
